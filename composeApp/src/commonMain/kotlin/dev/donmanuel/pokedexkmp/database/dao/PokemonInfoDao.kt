package dev.donmanuel.pokedexkmp.database.dao

import dev.donmanuel.pokedexkmp.database.PokedexDatabase
import dev.donmanuel.pokedexkmp.database.PokemonInfoEntity

class PokemonInfoDao(database: PokedexDatabase) {
    private val query = database.pokemonInfoEntityQueries

    internal fun selectOneByName(name: String): PokemonInfoEntity? {
        return query.selectOneByName(name = name).executeAsOneOrNull()
    }

    internal fun insert(pokemonInfoEntity: PokemonInfoEntity) {
        query.insertInfo(pokemonInfoEntity)
    }
}