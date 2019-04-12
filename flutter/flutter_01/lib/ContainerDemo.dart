import 'package:flutter/material.dart';

class ContainerDemo extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    var boxConstraints = BoxConstraints.expand(height: 311, width: 311);
    var background = Paint();
    background.color = Colors.green;
    var edgeInset = EdgeInsets.only(
        left: 110, right: 100, bottom: 100, top: 200);
    var gradColorList = List<Color>();
    gradColorList.add(Colors.blueAccent);
    gradColorList.add(Colors.orange);
    gradColorList.add(Colors.white);
    gradColorList.add(Colors.white);

    var linear = LinearGradient(
        colors: gradColorList,
        begin: Alignment.bottomRight
    );

    var radial = RadialGradient(
      colors: gradColorList,
      radius: 2,

    );
    var sweep = SweepGradient(
        colors: gradColorList,
        //startAngle:,
        endAngle: 7,
        center: Alignment.center
    );

    var decoration = BoxDecoration(
        color: Colors.pinkAccent,
        border: Border(
          top: BorderSide(width: 50, color: Colors.lightGreen),
          right: BorderSide(width: 20, color: Colors.pink),
          left: BorderSide(width: 30, color: Colors.teal),
          bottom: BorderSide(width: 30, color: Colors.lightGreen),
        ),
        gradient: sweep
    );


    return MaterialApp(
      title: 'Container Demo',
      home: Scaffold(
        body: Center(
          child: Container(
            child: Text('hello Container', style: TextStyle(
                fontSize: 22,
                background: background
            )),
            constraints: boxConstraints,
            //color: Colors.blueGrey,
            //在子节点下方绘制,子节点会调整位置
            decoration: decoration,
            //在子节点上方绘制,子节点不动,可能会被覆盖
            //foregroundDecoration :decoraion,
            padding: edgeInset,
            //margin: edgeInset,
            alignment: Alignment.bottomCenter,
          ),
        ),
      ),
    );
  }
}