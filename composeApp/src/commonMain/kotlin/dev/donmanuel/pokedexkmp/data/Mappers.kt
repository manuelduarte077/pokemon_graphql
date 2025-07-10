package dev.donmanuel.pokedexkmp.data

import dev.donmanuel.pokedexkmp.database.PokemonEntity
import dev.donmanuel.pokedexkmp.database.PokemonInfoEntity
import dev.donmanuel.pokedexkmp.model.Pokemon
import dev.donmanuel.pokedexkmp.model.PokemonInfo
import kotlinx.serialization.json.Json

fun Pokemon.toPokemonEntity(page: Long) = PokemonEntity(
    page = page,
    name = name,
    url = url
)

fun PokemonEntity.toPokemon() = Pokemon(
    page = page,
    name = name,
    url = url
)

fun PokemonInfo.toPokemonInfoEntity() = PokemonInfoEntity(
    id = id,
    name = name,
    height = height,
    weight = weight,
    types = Json.encodeToString(types)
)

fun PokemonInfoEntity.toPokemonInfo() = PokemonInfo(
    id = id,
    name = name,
    height = height,
    weight = weight,
    types = Json.decodeFromString(types)
)