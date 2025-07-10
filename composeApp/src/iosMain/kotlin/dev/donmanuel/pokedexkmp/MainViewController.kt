package dev.donmanuel.pokedexkmp

import androidx.compose.ui.window.ComposeUIViewController
import dev.donmanuel.pokedexkmp.di.initKoin
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    initKoin()
    return ComposeUIViewController {
        App()
    }
}