package dev.donmanuel.pokedexkmp.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.koin.android.ext.koin.androidContext
import org.koin.core.scope.Scope

actual fun Scope.databaseDriverFactory(): SqlDriver {
    return AndroidSqliteDriver(PokedexDatabase.Schema, androidContext(), "pokedex.db")
}