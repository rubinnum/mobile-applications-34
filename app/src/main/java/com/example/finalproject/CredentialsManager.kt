package com.example.finalproject

import androidx.core.util.PatternsCompat

class CredentialsManager {
    fun isEmailValid(email: String): Boolean =
        PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()

    fun isPasswordValid(password: String): Boolean = password.length >= 8

    fun userExists(email: String, password: String): Boolean =
        email == "test@te.st" && password == "12345678"
}