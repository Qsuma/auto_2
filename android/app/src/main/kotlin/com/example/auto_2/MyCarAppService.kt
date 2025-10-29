package com.example.auto_2

import android.content.Intent
import androidx.car.app.CarAppService
import androidx.car.app.Session
import androidx.car.app.Screen
import androidx.car.app.CarContext
import androidx.car.app.model.Template
import androidx.car.app.model.MessageTemplate
import androidx.car.app.validation.HostValidator

/**
 * Minimal Car App Service implementation for navigation-only example.
 * This provides a simple Screen showing a message. Extend this to add
 * navigation templates/actions following the Android for Cars library docs.
 */
class MyCarAppService : CarAppService() {
    override fun createHostValidator(): HostValidator {
    // For development/sample apps allow all hosts. For production, implement
    // a stricter validator according to Android for Cars guidance.
        // Try a few strategies to obtain a permissive HostValidator without
        // depending on a single constant name across library versions:
        // 1) Look for common static fields (ALLOW_ALL_HOSTS, ALLOW_ALL).
        // 2) Try to instantiate via a package-private constructor that some
        //    versions expose (three-arg constructor seen in some binaries).
        try {
            val fieldNames = arrayOf("ALLOW_ALL_HOSTS", "ALLOW_ALL", "ALLOW")
            for (name in fieldNames) {
                try {
                    val f = HostValidator::class.java.getField(name)
                    val v = f.get(null)
                    if (v is HostValidator) return v
                } catch (_: NoSuchFieldException) {
                    // try next
                }
            }

            // Try to instantiate using a declared constructor with three booleans
            // (some library builds have such a constructor). Use reflection to
            // bypass package-private visibility if present.
            try {
                val ctor = HostValidator::class.java.getDeclaredConstructor(
                    Boolean::class.javaPrimitiveType,
                    Boolean::class.javaPrimitiveType,
                    Boolean::class.javaPrimitiveType
                )
                ctor.isAccessible = true
                val instance = ctor.newInstance(true, true, true)
                if (instance is HostValidator) return instance
            } catch (_: NoSuchMethodException) {
                // fall through
            }
        } catch (_: Throwable) {
            // If reflection fails, fall through to returning a permissive
            // validator via a platform-typed cast on null to avoid compile error.
        }

        // Last-resort: attempt to fetch a field by name using getDeclaredField and
        // cast the result; if everything fails this will throw at runtime.
        @Suppress("UNCHECKED_CAST")
        return try {
            HostValidator::class.java.getDeclaredField("ALLOW_ALL_HOSTS").apply { isAccessible = true }
                .get(null) as HostValidator
        } catch (e: Throwable) {
            throw RuntimeException("Unable to obtain permissive HostValidator; check Android for Cars dependency version.", e)
        }
    }

    override fun onCreateSession(): Session {
        return object : Session() {
            override fun onCreateScreen(intent: Intent): Screen {
                // Return a dedicated LoginScreen implemented in a separate file.
                return LoginScreen(carContext)
            }
        }
    }
}
