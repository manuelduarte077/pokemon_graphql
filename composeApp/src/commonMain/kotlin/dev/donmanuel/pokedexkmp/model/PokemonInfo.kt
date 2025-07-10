package dev.donmanuel.pokedexkmp.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonInfo(
    val id: Long,
    val name: String,
    val height: Long,
    val weight: Long,
    val types: List<TypeWrapped>
) {
    constructor() : this(
        id = 0,
        name = "",
        height = 0,
        weight = 0,
        types = emptyList()
    )

    @Serializable
    data class TypeWrapped(
        val type: Type
    )

    @Serializable
    data class Type(
        val name: String
    )

    val idString
        get() = when (id.toString().length) {
            1 -> "00$id"
            2 -> "0$id"
            else -> "$id"
        }

    val imageUrl: String =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"

    val heightInMeter = "${height / 10f} m"
    val weightInKg = "${weight / 10f} kg"
}
