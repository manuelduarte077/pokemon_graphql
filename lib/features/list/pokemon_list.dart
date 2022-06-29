import 'package:flutter/material.dart';
import 'package:graphql_flutter/graphql_flutter.dart';
import 'package:pokemon/features/list/pokemon_list_card.dart';

import 'package:pokemon/__generated__/api.graphql.dart';

class PokemonList extends StatelessWidget {
  const PokemonList({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final query = PokemonListQuery();
    return Query(
      options: QueryOptions(
        document: query.document,
        // este es opcional
        operationName: query.operationName,
      ),
      builder: (result, {fetchMore, refetch}) {
        if (result.isLoading) {
          return const Center(child: CircularProgressIndicator.adaptive());
        }
        if (result.hasException) {
          return Center(child: Text(result.exception.toString()));
        }

        print("result is $result");
        final data = query.parse(result.data!);

        final cardList = data.pokemons!.results!.map((pokemon) {
          print("Pokemon Name: ${pokemon!.name}");
          return PokemonListCard(itemFrag: pokemon);
        }).toList();

        return RefreshIndicator(
          child: ListView(
            children: cardList,
          ),
          onRefresh: () async {
            await refetch!();
          },
        );
      },
    );
  }
}
