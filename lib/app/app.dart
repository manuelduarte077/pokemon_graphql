
import 'package:flutter/material.dart';
import 'package:graphql_flutter/graphql_flutter.dart';

import 'package:pokemon/features/detail/pokemon_detailScreen.dart';
import 'package:pokemon/features/list/pokemon_list_screen.dart';

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return GraphQLProvider(
      client: ValueNotifier(
        GraphQLClient(
          cache: GraphQLCache(store: HiveStore()),
          link: HttpLink('https://graphql-pokeapi.graphcdn.app'),
        ),
      ),
      child: MaterialApp(
        theme: ThemeData(
          useMaterial3: true,
        ),
        debugShowCheckedModeBanner: false,
        onGenerateRoute: (settings) {
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
        },
      ),
    );
  }
}
