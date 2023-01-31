import 'package:flutter/material.dart';
import 'package:pokemon/features/detail/pokemon_detailScreen.dart';
import 'package:pokemon/features/list/pokemon_list_screen.dart';

class AppRouter {
  static Route<T> onGenerateRoute<T>(RouteSettings settings) {
    switch (settings.name) {
      case '/pokemon':
        return MaterialPageRoute(
          settings: settings,
          builder: (_) => const PokemonDetailScreen(),
        );
      case '/':
      default:
        return MaterialPageRoute(
          builder: (_) => const PokemonListScreen(),
        );
    }
  }
}
