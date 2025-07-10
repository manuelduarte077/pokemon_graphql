package dev.donmanuel.pokedexkmp.ui.pokemonlist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.donmanuel.pokedexkmp.model.Pokemon
import dev.donmanuel.pokedexkmp.repository.PokemonListRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PokemonListViewModel(
    private val repository: PokemonListRepository
) : ViewModel() {
    private val _pokemonListState = mutableStateOf(PokemonListViewState())
    val pokemonListState: State<PokemonListViewState> get() = _pokemonListState

    private var isLastPageLoaded = false

    init {
        loadPokemonList()
    }

    fun loadPokemonList(
        onError: () -> Unit = { }
    ) = viewModelScope.launch {

        if (_pokemonListState.value.isLoading || _pokemonListState.value.isError || isLastPageLoaded) {
            return@launch
        }

        _pokemonListState.value = _pokemonListState.value.copy(
            isLoading = true
        )
        delay(500L)

        val currentPage = _pokemonListState.value.currentPage
        println("PokedexLogs repository.getPokemonList page: $currentPage")

        val result = repository.getPokemonList(
            page = _pokemonListState.value.currentPage,
            onLastPageLoaded = {
                isLastPageLoaded = true
            }
        )
        when {
            result.isSuccess -> {
                val pokemons = result.getOrDefault(emptyList())
                _pokemonListState.value = _pokemonListState.value.copy(
                    pokemons = pokemons,
                    currentPage = currentPage + 1,
                    isLoading = false,
                    isError = false
                )
            }
            result.isFailure -> {
                _pokemonListState.value = _pokemonListState.value.copy(
                    isLoading = false,
                    isError = true
                )
                onError()
            }
        }
        println("PokedexLogs repository.getPokemonList size: ${_pokemonListState.value.pokemons.size}")
    }

    fun resetErrorState() {
        _pokemonListState.value = _pokemonListState.value.copy(
            isError = false
        )
    }
}

data class PokemonListViewState(
    val pokemons: List<Pokemon> = emptyList(),
    val currentPage: Long = 0,
    val isLoading:Boolean = false,
    val isError: Boolean = false
)