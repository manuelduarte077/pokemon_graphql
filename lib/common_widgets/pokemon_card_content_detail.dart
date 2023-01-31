import 'package:flutter/material.dart';

class PokemonCardContentDetail extends StatelessWidget {
  const PokemonCardContentDetail({
    super.key,
    this.namePokemon,
    this.style,
    this.abilityPokemon,
    this.typePokemon,
  });

  final String? namePokemon;
  final String? typePokemon;
  final List? abilityPokemon;
  final TextStyle? style;

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: const EdgeInsets.fromLTRB(18, 50, 16, 16),
      constraints: const BoxConstraints.expand(),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          Text(
            namePokemon!,
            style: style,
          ),
          const SizedBox(
            height: 4,
          ),
          Container(
            margin: const EdgeInsets.symmetric(vertical: 8.0),
            height: 2.0,
            width: 18.0,
            color: const Color(0xff00c6ff),
          ),
          PokemonCardContentDetailStats(
            abilityPokemon: abilityPokemon,
            typePokemon: typePokemon,
          ),
        ],
      ),
    );
  }
}

class PokemonCardContentDetailStats extends StatelessWidget {
  const PokemonCardContentDetailStats({
    Key? key,
    this.abilityPokemon,
    this.typePokemon,
  }) : super(key: key);

  final List? abilityPokemon;
  final String? typePokemon;

  @override
  Widget build(BuildContext context) {
    return Row(
      children: [
        Expanded(
          child: Row(
            children: [
              const SizedBox(
                width: 8,
              ),
              const Icon(
                Icons.query_stats,
                color: Colors.greenAccent,
              ),
              const SizedBox(
                width: 10,
              ),
              Text(
                abilityPokemon!.join(', '),
                style: const TextStyle(
                  fontSize: 14,
                  color: Colors.white,
                ),
              ),
            ],
          ),
        ),
        Expanded(
          child: Row(
            children: [
              const Icon(
                Icons.numbers,
                color: Colors.greenAccent,
              ),
              Text(
                typePokemon!,
                style: const TextStyle(
                  fontSize: 14,
                  color: Colors.white,
                ),
              ),
            ],
          ),
        ),
      ],
    );
  }
}
