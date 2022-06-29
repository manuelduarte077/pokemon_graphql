import 'package:flutter/material.dart';

class PokemonCardContent extends StatelessWidget {
  const PokemonCardContent({Key? key, this.namePokemon}) : super(key: key);

  final String? namePokemon;

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: const EdgeInsets.fromLTRB(76, 16, 16, 16),
      constraints: const BoxConstraints.expand(),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          const SizedBox(height: 4),
          Text(
            namePokemon!,
            style: const TextStyle(fontSize: 22, color: Colors.white),
          ),
          const SizedBox(
            height: 4,
          ),
          const Text(
            'subTitle',
            style: TextStyle(fontSize: 16, color: Colors.white),
          ),
          Container(
            margin: const EdgeInsets.symmetric(vertical: 8.0),
            height: 2.0,
            width: 18.0,
            color: const Color(0xff00c6ff),
          ),
          Row(
            children: [
              Expanded(
                child: Row(
                  children: const [
                    Icon(
                      Icons.query_stats,
                      size: 12,
                      color: Colors.greenAccent,
                    ),
                    SizedBox(
                      width: 5,
                    ),
                    Text(
                      'Stats',
                      style: TextStyle(
                        fontSize: 14,
                        color: Colors.white,
                      ),
                    ),
                  ],
                ),
              ),
              Expanded(
                child: Row(
                  children: const [
                    Icon(
                      Icons.numbers,
                      size: 12,
                      color: Colors.greenAccent,
                    ),
                    SizedBox(
                      width: 5,
                    ),
                    Text(
                      'Numbers',
                      style: TextStyle(
                        fontSize: 14,
                        color: Colors.white,
                      ),
                    ),
                  ],
                ),
              ),
            ],
          ),
        ],
      ),
    );
  }
}
