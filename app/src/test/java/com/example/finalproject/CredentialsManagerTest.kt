package com.example.finalproject


import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CredentialsManagerTest {

    private val credentialsManager = CredentialsManager()

    @Test
    fun shouldReturnFalseForEmptyEmail() {
        assertFalse(credentialsManager.isEmailValid(""))
    }

    @Test
    fun shouldReturnFalseForWrongFormatEmail() {
        assertFalse(credentialsManager.isEmailValid("invalid-email"))
    }

    @Test
    fun shouldReturnTrueForValidEmail() {
        assertTrue(credentialsManager.isEmailValid("example@email.com"))
    }

    @Test
    fun shouldReturnFalseForEmptyPassword() {
        assertFalse(credentialsManager.isPasswordValid(""))
    }

    @Test
    fun shouldReturnFalseForPasswordWithLessThanEightChars() {
        assertFalse(credentialsManager.isPasswordValid("1234567"))
    }

    @Test
    fun shouldReturnTrueForValidPassword() {
        assertTrue(credentialsManager.isPasswordValid("password123"))
    }
}