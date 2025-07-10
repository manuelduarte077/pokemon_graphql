package dev.donmanuel.pokedexkmp

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import dev.donmanuel.pokedexkmp.navigation.AppNavigation
import dev.donmanuel.pokedexkmp.ui.theme.PokedexTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    PokedexTheme {
        AppNavigation()
    }
}