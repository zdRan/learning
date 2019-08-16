import 'package:flutter/material.dart';

class RowDemo extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'RowDemo',
      home: Scaffold(
        body: Center(
          child:Row(
            children: <Widget>[
              Expanded(
                child: Text('1',textAlign: TextAlign.center,style: TextStyle(color: Colors.blue))
              ),
              Expanded(
                child: Text('2',textAlign: TextAlign.center,style: TextStyle(color: Colors.red),)
                )
            ],
          )
      ),
    );
  }
}