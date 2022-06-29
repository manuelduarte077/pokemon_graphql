import 'package:flutter/material.dart';
import 'package:pokemon/__generated__/api.graphql.dart';
import 'package:pokemon/common_widgets/pokemon_card_content.dart';
import 'package:pokemon/common_widgets/widgets.dart';

import 'package:pokemon/features/detail/pokemon_detailScreen.dart';

class PokemonListCard extends StatelessWidget {
  const PokemonListCard({
    Key? key,
    required this.itemFrag,
  }) : super(key: key);

  final PokemonListCardItemMixin itemFrag;

  @override
  Widget build(BuildContext context) {
    void handleTap() {
      if (itemFrag.name == null) {
        ScaffoldMessenger.of(context).showSnackBar(
            const SnackBar(content: Text("Unable to load this Pokemon")));
      } else {
        Navigator.pushNamed(context, PokemonDetailScreen.routeName,
            arguments: PokemonDetailScreenArgs(itemFrag.name!));
      }
    }

    return Container(
      margin: const EdgeInsets.symmetric(
        vertical: 16,
        horizontal: 24,
      ),
      child: Stack(
        children: [
          PokemonCard(
            contentCard: PokemonCardContent(
              namePokemon: itemFrag.name ?? "<No Name>",
              style: const TextStyle(fontSize: 22, color: Colors.white),
            ),
            onTap: handleTap,
          ),
          PokemonThumbnail(
            image: itemFrag.image != 'null'
                ? Image.network(itemFrag.image!)
                : null,
          ),
        ],
      ),
    );
  }
}
