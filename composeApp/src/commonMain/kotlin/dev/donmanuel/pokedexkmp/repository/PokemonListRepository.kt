package dev.donmanuel.pokedexkmp.repository

import dev.donmanuel.pokedexkmp.data.toPokemon
import dev.donmanuel.pokedexkmp.data.toPokemonEntity
import dev.donmanuel.pokedexkmp.database.dao.PokemonDao
import dev.donmanuel.pokedexkmp.model.Pokemon
import dev.donmanuel.pokedexkmp.network.PokemonApiService

class PokemonListRepository(
    private val apiService: PokemonApiService,
    private val pokemonDao: PokemonDao
) {

    suspend fun getPokemonList(page: Long, onLastPageLoaded: () -> Unit): Result<List<Pokemon>> {
        val localPokemonList = pokemonDao.selectAllByPage(page = page)
        println("PokedexLogs localPokemonList size: ${localPokemonList.size}")
        return if (localPokemonList.isEmpty()) {
            try {
                val pokemons = apiService.getPokemonList(page = page).map { it.copy(page = page) }
                println("PokedexLogs apiService.getPokemonList size: ${pokemons.size}")
                if (pokemons.isEmpty()) {
                    onLastPageLoaded()
                }
                pokemons.forEach { pokemon ->
                    pokemonDao.insert(pokemon.toPokemonEntity(page))
                }
                Result.success(
                    pokemonDao.selectAllUntilPage(page = page).map { it.toPokemon() }
                )
            } catch (e: Exception) {
                println("PokedexLogs error: $e")
                Result.failure(e)
            }
        } else {
            Result.success(
                pokemonDao.selectAllUntilPage(page = page).map { it.toPokemon() }
            )
        }
    }
}