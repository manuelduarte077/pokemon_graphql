import 'package:flutter/material.dart';

class PokemonCard extends StatelessWidget {
  const PokemonCard({super.key, required this.contentCard, this.onTap});

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
          borderRadius: BorderRadius.circular(8),
          boxShadow: const [
            BoxShadow(
              color: Colors.black12,
              blurRadius: 10,
              offset: Offset(0, 10),
            )
          ],
        ),
        child: contentCard,
      ),
    );
  }
}
