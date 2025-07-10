package dev.donmanuel.pokedexkmp.di

import dev.donmanuel.pokedexkmp.database.di.databaseModule
import dev.donmanuel.pokedexkmp.network.PokemonApiService
import dev.donmanuel.pokedexkmp.repository.PokemonInfoRepository
import dev.donmanuel.pokedexkmp.repository.PokemonListRepository
import dev.donmanuel.pokedexkmp.ui.pokemonInfo.PokemonInfoViewModel
import dev.donmanuel.pokedexkmp.ui.pokemonlist.PokemonListViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

private val appModule = module {
    single<PokemonApiService> { PokemonApiService() }

    single<PokemonListRepository> { PokemonListRepository(apiService = get(), pokemonDao = get()) }
    viewModel { PokemonListViewModel(repository = get()) }

    single<PokemonInfoRepository> {
        PokemonInfoRepository(
            apiService = get(),
            pokemonInfoDao = get()
        )
    }
    viewModel { PokemonInfoViewModel(get(), repository = get()) }
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(appModule, databaseModule)
    }