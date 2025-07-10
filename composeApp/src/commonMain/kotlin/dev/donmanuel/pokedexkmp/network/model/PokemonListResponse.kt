package dev.donmanuel.pokedexkmp.network.model

import dev.donmanuel.pokedexkmp.model.Pokemon
import kotlinx.serialization.Serializable

@Serializable
data class PokemonListResponse(
    val results: List<Pokemon>
)
