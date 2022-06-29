import 'package:graphql_flutter/graphql_flutter.dart';
import 'package:flutter/material.dart';
import '../../__generated__/api.graphql.dart';
import '../../common_widgets/pokemon_card_content_detail.dart';
import '../../common_widgets/pokemon_card_detail.dart';
import '../../common_widgets/pokemon_thumbnail_detail.dart';

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

            return Container(
              margin: const EdgeInsets.symmetric(
                vertical: 16,
                horizontal: 24,
              ),
              child: Column(
                children: [
                  Stack(
                    children: [
                      PokemonCardDetail(
                        contentCardDetail: PokemonCardContentDetail(
                          namePokemon: args.name,
                          style: const TextStyle(
                            color: Colors.white,
                            fontSize: 24,
                          ),
                          abilityPokemon: data.pokemon?.types
                                  ?.map((ability) => ability!.type!.name)
                                  .toList() ??
                              [],
                          typePokemon:
                              data.pokemon?.types?.first?.type?.name ?? '',
                        ),
                      ),
                      PokemonThumbnailDetail(
                        image: Image.network(
                          data.pokemon!.sprites!.frontDefault!,
                        ),
                      ),
                    ],
                  ),
                ],
              ),
            );
          },
        ),
      ),
    );
  }
}
