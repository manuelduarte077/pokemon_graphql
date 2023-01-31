import 'package:flutter/material.dart';

class PokemonCardContent extends StatelessWidget {
  const PokemonCardContent({super.key, this.namePokemon, this.style});

  final String? namePokemon;
  final TextStyle? style;

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: const EdgeInsets.fromLTRB(76, 30, 16, 16),
      constraints: const BoxConstraints.expand(),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Text(
            namePokemon!,
            style: style,
          ),
          const SizedBox(
            height: 4,
          ),
          Container(
            margin: const EdgeInsets.symmetric(vertical: 8),
            height: 2,
            width: 18,
            color: const Color(0xff00c6ff),
          ),
        ],
      ),
    );
  }
}
