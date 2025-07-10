package dev.donmanuel.pokedexkmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform