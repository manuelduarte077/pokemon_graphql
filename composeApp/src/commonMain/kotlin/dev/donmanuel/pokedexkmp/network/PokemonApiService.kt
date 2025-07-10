package dev.donmanuel.pokedexkmp.network

import dev.donmanuel.pokedexkmp.model.Pokemon
import dev.donmanuel.pokedexkmp.model.PokemonInfo
import dev.donmanuel.pokedexkmp.network.model.PokemonListResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class PokemonApiService {

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }

    suspend fun getPokemonList(
        page: Long,
    ): List<Pokemon> {
        return httpClient.get(LIST_URL) {
            url {
                parameters.append("limit", PAGE_SIZE.toString())
                parameters.append("offset", (page * PAGE_SIZE).toString())
            }
            contentType(ContentType.Application.Json)
        }.body<PokemonListResponse>().results
    }

    suspend fun getPokemonInfo(
        name: String,
    ): PokemonInfo {
        return httpClient.get("$LIST_URL/$name") {
            contentType(ContentType.Application.Json)
        }.body<PokemonInfo>()
    }

    companion object {
        private const val BASE_URL = "https://pokeapi.co/api/v2/"
        private const val LIST_URL = BASE_URL + "pokemon"
        private const val PAGE_SIZE = 10
    }
}