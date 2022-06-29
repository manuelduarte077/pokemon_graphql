import 'package:flutter/material.dart';
import 'package:graphql_flutter/graphql_flutter.dart';

import 'app/app.dart';

void main() async {
  await initHiveForFlutter();
  runApp(const MyApp());
}
