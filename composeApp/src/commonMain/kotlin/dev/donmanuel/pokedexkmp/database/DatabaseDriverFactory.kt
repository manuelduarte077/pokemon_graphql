package dev.donmanuel.pokedexkmp.database

import app.cash.sqldelight.db.SqlDriver
import org.koin.core.scope.Scope

expect fun Scope.databaseDriverFactory(): SqlDriver