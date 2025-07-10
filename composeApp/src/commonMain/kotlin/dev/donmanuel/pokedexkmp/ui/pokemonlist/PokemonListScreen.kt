package dev.donmanuel.pokedexkmp.ui.pokemonlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import dev.donmanuel.pokedexkmp.ui.composables.PokemonListContent
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
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(
                        text = stringResource(Res.string.app_name),
                        style = MaterialTheme.typography.headlineSmall
                    )
                },
                scrollBehavior = scrollBehavior
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
                        Text("OK", style = MaterialTheme.typography.bodyLarge)
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

