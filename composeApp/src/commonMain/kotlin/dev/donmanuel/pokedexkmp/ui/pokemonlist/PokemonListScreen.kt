package dev.donmanuel.pokedexkmp.ui.pokemonlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import dev.donmanuel.pokedexkmp.model.Pokemon
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import pokedexkmp.composeapp.generated.resources.Res
import pokedexkmp.composeapp.generated.resources.app_name

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PokemonListScreen(
    onPokemonClick: (name: String) -> Unit
) {
    val viewModel = koinViewModel<PokemonListViewModel>()
    val state = viewModel.pokemonListState.value

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(Res.string.app_name),
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            )
        }
    ) { innerPadding ->
        val openDialog = remember { mutableStateOf(false) }

        if (openDialog.value) {
            AlertDialog(
                onDismissRequest = {
                    viewModel.resetErrorState()
                    openDialog.value = false
                                   },
                title = {
                    Text(text = "Error")
                        },
                text = {
                    Text("Encountered an error while loading data")
                       },
                confirmButton = {
                    Button(
                        onClick = {
                            viewModel.resetErrorState()
                            openDialog.value = false
                        }
                    ) {
                        Text("OK")
                    }
                }
            )
        }

        Box(modifier = Modifier.padding(innerPadding)) {
            PokemonListContent(
                pokemons = state.pokemons,
                isLoading = state.isLoading,
                loadPokemonList = {
                    viewModel.loadPokemonList(
                        onError = {
                            openDialog.value = true
                        }
                    )
                },
                onPokemonClick = onPokemonClick
            )
        }
    }
}

@Composable
private fun PokemonListContent(
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

    // load more if scrolled to bottom
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
                Loading(modifier = Modifier.fillParentMaxWidth())
            }
        }
    }
}

@Composable
private fun Loading(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun PokemonItem(
    pokemon: Pokemon,
    onItemClick: (name: String) -> Unit
) {
    Card(
        onClick = {
            onItemClick(pokemon.name)
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
            AsyncImage(
                model = pokemon.imageUrl,
                contentDescription = pokemon.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(100.dp)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize().padding(16.dp)
            ) {
                Text(
                    text = pokemon.name.replaceFirstChar(Char::titlecase),
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold)
                )
                Text(
                    text = pokemon.numberString,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}