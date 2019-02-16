import 'package:flutter/material.dart';

class ContainerDemo extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
      return MaterialApp(
        title: 'Container Demo',
        home: Scaffold(
          body: Center(
            child: Container(
              child: Text('hello Container'),

              alignment: Alignment.bottomCenter,
            ),
          ),
        ),
      );
  }
}