import 'package:flutter/material.dart';
import 'package:pokemon/__generated__/api.graphql.dart';
import 'package:pokemon/features/detail/PokemonDetailScreen.dart';

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

    return Card(
      child: ListTile(
        onTap: handleTap,
        leading: itemFrag.image != null ? Image.network(itemFrag.image!) : null,
        title: Text(itemFrag.name ?? "<No Name>"),
      ),
    );
  }
}
