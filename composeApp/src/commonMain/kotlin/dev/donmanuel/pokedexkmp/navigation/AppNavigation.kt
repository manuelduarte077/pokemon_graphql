package dev.donmanuel.pokedexkmp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.donmanuel.pokedexkmp.ui.pokemonInfo.PokemonInfoScreen
import dev.donmanuel.pokedexkmp.ui.pokemonlist.PokemonListScreen
import kotlinx.serialization.Serializable

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ListScreen) {
        composable<ListScreen> {
            PokemonListScreen(onPokemonClick = {
                navController.navigate(InfoScreen(name = it))
            })
        }
        composable<InfoScreen> {
            PokemonInfoScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}

@Serializable
object ListScreen

@Serializable
class InfoScreen(val name: String)