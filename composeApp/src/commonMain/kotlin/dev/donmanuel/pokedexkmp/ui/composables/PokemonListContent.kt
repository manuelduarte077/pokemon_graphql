package dev.donmanuel.pokedexkmp.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.donmanuel.pokedexkmp.model.Pokemon

@Composable
fun PokemonListContent(
    pokemons: List<Pokemon>,
    isLoading: Boolean,
    loadPokemonList: () -> Unit,
    onPokemonClick: (name: String) -> Unit
) {
    val buffer = 1
    val listState: LazyListState = rememberLazyListState()

    // observe list scrolling
    val reachedBottom: Boolean by remember {
        derivedStateOf {
            val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
            lastVisibleItem?.index != 0 && lastVisibleItem?.index == listState.layoutInfo.totalItemsCount - buffer
        }
    }

    LaunchedEffect(reachedBottom) {
        println("PokedexLogs reachedBottom: $reachedBottom")
        if (reachedBottom) {
            loadPokemonList()
        }
    }

    LazyColumn(
        state = listState,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(pokemons) { pokemon ->
            PokemonItem(
                pokemon = pokemon,
                onItemClick = onPokemonClick
            )
        }

        if (isLoading) {
            item(key = "loading-more") {
                LoadingContent(modifier = Modifier.fillParentMaxWidth())
            }
        }
    }
}