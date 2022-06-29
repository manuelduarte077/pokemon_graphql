import 'package:flutter/material.dart';
import 'package:pokemon/common_widgets/pokemon_card_content.dart';

class PokemonCard extends StatelessWidget {
  const PokemonCard({Key? key, required this.contentCard, this.onTap})
      : super(key: key);

  final Widget? contentCard;
  final Function()? onTap;

  @override
  Widget build(BuildContext context) {
    return InkWell(
      onTap: onTap,
      child: Container(
        height: 135,
        margin: const EdgeInsets.only(left: 46),
        decoration: BoxDecoration(
          color: const Color(0xFF333366),
          shape: BoxShape.rectangle,
          borderRadius: BorderRadius.circular(8.0),
          boxShadow: const [
            BoxShadow(
              color: Colors.black12,
              blurRadius: 10,
              offset: Offset(0.0, 10.0),
            )
          ],
        ),
        child: contentCard,
      ),
    );
  }
}
