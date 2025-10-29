package com.example.auto_2

import android.content.Intent
import androidx.car.app.CarAppService
import androidx.car.app.Session
import androidx.car.app.Screen
import androidx.car.app.CarContext
import androidx.car.app.model.Template
import androidx.car.app.model.MessageTemplate

/**
 * Minimal Car App Service implementation for navigation-only example.
 * This provides a simple Screen showing a message. Extend this to add
 * navigation templates/actions following the Android for Cars library docs.
 */
class MyCarAppService : CarAppService() {
    override fun createSession(): Session {
        return object : Session() {
            override fun onCreateScreen(intent: Intent?): Screen {
                return object : Screen(carContext) {
                    override fun onGetTemplate(): Template {
                        return MessageTemplate.Builder("Auto Demo - Navegación")
                            .setTitle("Demo navegación")
                            .build()
                    }
                }
            }
        }
    }
}
