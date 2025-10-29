package com.example.auto_2

import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity : FlutterActivity() {
	private val CHANNEL = "com.example.auto_2/native_car"

	override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
		super.configureFlutterEngine(flutterEngine)

		MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler { call, result ->
			when (call.method) {
				"getCarStatus" -> {
					// Placeholder implementation. Integrate with Android for Cars APIs here.
					val status = "android_native_ok"
					result.success(status)
				}
				"startNavigation" -> {
					val destination = call.argument<String>("destination") ?: "unknown"
					// Placeholder: trigger navigation via Android for Cars when integrated.
					result.success("navigation_started:$destination")
				}
				else -> result.notImplemented()
			}
		}
	}
}
