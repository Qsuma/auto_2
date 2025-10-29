package com.example.auto_2

import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.MessageTemplate
import androidx.car.app.model.Template

/**
 * Simple login screen implemented using MessageTemplate so it compiles
 * with a broad range of androidx.car.app versions. This displays a title
 * and instructions (the real credential input should happen on the phone).
 */
class LoginScreen(carContext: CarContext) : Screen(carContext) {
    override fun onGetTemplate(): Template {
        return MessageTemplate.Builder("Introduce tus credenciales en el teléfono para iniciar sesión.")
            .setTitle("Iniciar sesión")
            .build()
    }
}
