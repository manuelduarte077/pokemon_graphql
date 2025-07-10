package dev.donmanuel.pokedexkmp.database.di

import dev.donmanuel.pokedexkmp.database.PokedexDatabase
import dev.donmanuel.pokedexkmp.database.dao.PokemonDao
import dev.donmanuel.pokedexkmp.database.dao.PokemonInfoDao
import dev.donmanuel.pokedexkmp.database.databaseDriverFactory
import org.koin.dsl.module

val databaseModule = module {
    factory { databaseDriverFactory() }
    single<PokedexDatabase> { PokedexDatabase(driver = get()) }
    single<PokemonDao> { PokemonDao(database = get()) }
    single<PokemonInfoDao> { PokemonInfoDao(database = get()) }
}