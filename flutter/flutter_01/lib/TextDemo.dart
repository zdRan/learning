import 'package:flutter/material.dart';

void main() => runApp(TextDemo());
class TextDemo extends StatelessWidget{
  
  @override
  Widget build(BuildContext context) {
    var background = Paint();
    background.color = Color.fromARGB(111, 255, 1, 1);
    var foreground = Paint();
    foreground.color =  Color.fromARGB(255, 1, 122, 11);

    var shadowsList = List<Shadow>();
    shadowsList.add(Shadow(
      color: Color.fromARGB(111, 255, 1, 1),
      offset: Offset(1, 20),
      blurRadius:1));
    shadowsList.add(Shadow(
      color: Color.fromARGB(255, 1, 122, 11),
      offset: Offset(20, 1),
      blurRadius:10));

    return MaterialApp(
      title: 'Text Demo',
      home: Scaffold(
        body: Center(
          child: Text(
            'hello ,text,好好学习,天天向上!好好学习,天天向上!好好学习,天天向上!好好学习,天天向上!好好学习,天天向上',
            textAlign: TextAlign.start,
            textDirection: TextDirection.rtl,
            maxLines: 2,
            overflow: TextOverflow.fade ,
            //textScaleFactor: 1,
            style: TextStyle(
              //background: background,
              //color: Color.fromARGB(255, 1, 122, 11),
              decoration: TextDecoration.underline,
              decorationColor: Color.fromRGBO(255, 1, 1, 11),
              decorationStyle: TextDecorationStyle.wavy ,
              fontSize: 44,
              fontStyle: FontStyle.normal,
              fontWeight: FontWeight.w900,
              //与color 值冲突
              foreground: foreground,
              height: 1.5,
              letterSpacing: 11,
              //shadows: shadowsList,
              wordSpacing: 122
              ),
              ),
              
        ),
      ),
    );
  }

} 