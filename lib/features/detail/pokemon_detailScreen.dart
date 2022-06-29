import 'package:flutter/material.dart';
import 'package:graphql_flutter/graphql_flutter.dart';

import '../../__generated__/api.graphql.dart';

class PokemonDetailScreenArgs {
  final String name;

  PokemonDetailScreenArgs(this.name);
}

class PokemonDetailScreen extends StatelessWidget {
  const PokemonDetailScreen({Key? key}) : super(key: key);

  static String routeName = '/pokemon';

  @override
  Widget build(BuildContext context) {
    final args =
        ModalRoute.of(context)!.settings.arguments as PokemonDetailScreenArgs;
    final query = PokemonDetailScreenQuery(
      variables: PokemonDetailScreenArguments(name: args.name),
    );
    return Scaffold(
      appBar: AppBar(title: Text(args.name)),
      body: Center(
        child: Query(
          options: QueryOptions(
            document: query.document,
            variables: query.variables.toJson(),
          ),
          builder: (result, {fetchMore, refetch}) {
            if (result.isLoading) {
              return const Center(
                child: CircularProgressIndicator.adaptive(),
              );
            }
            if (result.hasException) return Text(result.exception.toString());

            final data = query.parse(result.data!);
            final pokemon = data.pokemon;
            if (pokemon == null) Navigator.pop(context);

            return Column(
              children: [
                Image.network(data.pokemon!.sprites!.frontDefault!),
                Center(
                  child: Text(
                    args.name,
                    style: const TextStyle(fontSize: 24),
                  ),
                )
              ],
            );
          },
        ),
      ),
    );
  }
}
