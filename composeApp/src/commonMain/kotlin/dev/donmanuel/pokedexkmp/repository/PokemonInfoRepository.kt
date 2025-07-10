package dev.donmanuel.pokedexkmp.repository

import dev.donmanuel.pokedexkmp.data.toPokemonInfo
import dev.donmanuel.pokedexkmp.data.toPokemonInfoEntity
import dev.donmanuel.pokedexkmp.database.dao.PokemonInfoDao
import dev.donmanuel.pokedexkmp.model.PokemonInfo
import dev.donmanuel.pokedexkmp.network.PokemonApiService

class PokemonInfoRepository(
    private val apiService: PokemonApiService,
    private val pokemonInfoDao: PokemonInfoDao
) {

    suspend fun getPokemonInfo(name: String): Result<PokemonInfo> {
        val localPokemonInfo = pokemonInfoDao.selectOneByName(name = name)

        return if (localPokemonInfo == null) {
            try {
                val response = apiService.getPokemonInfo(name = name)
                pokemonInfoDao.insert(response.toPokemonInfoEntity())
                Result.success(response)
            } catch (e: Exception) {
                Result.failure(e)
            }
        } else {
            Result.success(localPokemonInfo.toPokemonInfo())
        }
    }
}