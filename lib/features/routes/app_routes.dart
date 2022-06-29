import 'package:flutter/material.dart';
import 'package:pokemon/features/detail/pokemon_detailScreen.dart';
import 'package:pokemon/features/list/pokemon_list_screen.dart';

class AppRouter {
  static Route onGenerateRoute(RouteSettings settings) {
    print('This is route: ${settings.name}');

    switch (settings.name) {
      case '/pokemon':
        return MaterialPageRoute(
          settings: settings,
          builder: (_) => const PokemonDetailScreen(),
        );
        break;
      case '/':
      default:
        return MaterialPageRoute(
          builder: (_) => const PokemonListScreen(),
        );
    }
  }
}
