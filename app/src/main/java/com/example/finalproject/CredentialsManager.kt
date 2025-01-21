package com.example.finalproject

import androidx.core.util.PatternsCompat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object CredentialsManager {
    private var registeredUsers: MutableMap<String, String> = mutableMapOf()
    private val _loginState = MutableStateFlow<LoginState>(LoginState.LoggedOut)
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    sealed class LoginState {
        data object LoggedOut : LoginState()
        data class LoggedIn(val email: String) : LoginState()
    }

    fun isEmailValid(email: String): Boolean =
        PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()

    fun isPasswordValid(password: String): Boolean = password.length >= 8

    fun userExists(email: String, password: String): Boolean {
        val exists = email == "test@te.st" && password == "12345678"
        if (exists) {
            _loginState.value = LoginState.LoggedIn(email)
        }
        return exists
    }

    fun registerUser(email: String, password: String) {
        if (!areCredentialsValid(email, password))
            throw IllegalArgumentException("Invalid credentials")

        if (registeredUsers.containsKey(email.lowercase()))
            throw IllegalArgumentException("User already exists")

        insertCaseInsensitiveEntity(email, password)
        _loginState.value = LoginState.LoggedIn(email)
    }

    fun logout() {
        _loginState.value = LoginState.LoggedOut
    }

    private fun insertCaseInsensitiveEntity(email: String, password: String) {
        registeredUsers[email.lowercase()] = password
    }

    private fun areCredentialsValid(email: String, password: String): Boolean =
        isEmailValid(email) && isPasswordValid(password)
}