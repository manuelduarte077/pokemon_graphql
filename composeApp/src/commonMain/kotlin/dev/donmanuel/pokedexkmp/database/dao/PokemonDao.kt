package dev.donmanuel.pokedexkmp.database.dao

import dev.donmanuel.pokedexkmp.database.PokedexDatabase
import dev.donmanuel.pokedexkmp.database.PokemonEntity

class PokemonDao(database: PokedexDatabase) {
    private val query = database.pokemonEntityQueries

    internal fun selectAllUntilPage(page: Long): List<PokemonEntity> {
        return query.selectAllUntilPage(page = page).executeAsList()
    }

    internal fun selectAllByPage(page: Long): List<PokemonEntity> {
        return query.selectAllByPage(page = page).executeAsList()
    }

    internal fun insert(pokemonEntity: PokemonEntity) {
        query.insert(pokemonEntity)
    }
}