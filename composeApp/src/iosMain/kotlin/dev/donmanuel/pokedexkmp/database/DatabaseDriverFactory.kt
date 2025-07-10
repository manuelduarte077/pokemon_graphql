package dev.donmanuel.pokedexkmp.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import org.koin.core.scope.Scope

actual fun Scope.databaseDriverFactory(): SqlDriver {
    return NativeSqliteDriver(PokedexDatabase.Schema, "pokedex.db")
}