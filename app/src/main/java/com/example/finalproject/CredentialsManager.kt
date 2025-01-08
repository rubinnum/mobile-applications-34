package com.example.finalproject

import androidx.core.util.PatternsCompat

class CredentialsManager {
    var registeredUsers: MutableMap<String, String> = mutableMapOf()

    fun isEmailValid(email: String): Boolean =
        PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()

    fun isPasswordValid(password: String): Boolean = password.length >= 8

    fun userExists(email: String, password: String): Boolean =
        email == "test@te.st" && password == "12345678"

    fun registerUser(email: String, password: String) {
        if (!areCredentialsValid(email, password))
            throw IllegalArgumentException("Invalid credentials")

        if (registeredUsers.containsKey(email.lowercase()))
            throw IllegalArgumentException("User already exists")

        insertCaseInsensitiveEntity(email, password)
    }

    private fun insertCaseInsensitiveEntity(email: String, password: String) {
        registeredUsers[email.lowercase()] = password
    }

    private fun areCredentialsValid(email: String, password: String): Boolean =
        isEmailValid(email) && isPasswordValid(password)
}