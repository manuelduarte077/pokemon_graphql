import 'package:flutter/material.dart';
import 'package:pokemon/features/list/pokemon_list.dart';

class PokemonListScreen extends StatelessWidget {
  const PokemonListScreen({super.key});

  static const String routeName = '/';

  static Route<T> route<T>() {
    return MaterialPageRoute(
      settings: const RouteSettings(name: routeName),
      builder: (context) => const PokemonListScreen(),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Pokemon GO'),
        actions: const [
          Padding(
            padding: EdgeInsets.symmetric(horizontal: 15),
            child: Icon(
              Icons.catching_pokemon,
              size: 35,
              color: Colors.greenAccent,
            ),
          )
        ],
      ),
      body: const PokemonList(),
    );
  }
}
