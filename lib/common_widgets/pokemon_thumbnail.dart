import 'package:flutter/material.dart';

class PokemonThumbnail extends StatelessWidget {
  const PokemonThumbnail({Key? key, required this.image}) : super(key: key);
  final Image? image;

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: const EdgeInsets.symmetric(vertical: 16),
      alignment: FractionalOffset.centerLeft,
      child: image,
    );
  }
}
