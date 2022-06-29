import 'package:flutter/material.dart';

class PokemonCardDetail extends StatelessWidget {
  const PokemonCardDetail({Key? key, this.contentCardDetail}) : super(key: key);

  final Widget? contentCardDetail;

  @override
  Widget build(BuildContext context) {
    return Container(
      height: 150,
      margin: const  EdgeInsets.only(top: 60),
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
      child: contentCardDetail,
    );
  }
}
