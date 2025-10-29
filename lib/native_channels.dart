import 'package:flutter/services.dart';

class NativeChannels {
  static const MethodChannel _channel = MethodChannel('com.example.auto_2/native_car');

  static Future<String?> getCarStatus() async {
    try {
      final String? res = await _channel.invokeMethod('getCarStatus');
      return res;
    } on PlatformException {
      return null;
    }
  }

  static Future<String?> startNavigation(String destination) async {
    try {
      final String? res = await _channel.invokeMethod('startNavigation', {'destination': destination});
      return res;
    } on PlatformException {
      return null;
    }
  }
}
