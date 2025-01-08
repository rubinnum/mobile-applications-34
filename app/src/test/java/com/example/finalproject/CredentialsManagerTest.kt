package com.example.finalproject


import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

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

    @Test
    fun shouldReturnTrueForHardcodedCredentials() {
        // given
        val email = "test@te.st"
        val password = "12345678"

        // when
        val result = credentialsManager.userExists(email, password)

        // then
        assertTrue(result)
    }

    @Test
    fun shouldRegisterUser() {
        // given
        val email = "valid.email@gmail.com"
        val password = "password123"

        // when
        credentialsManager.registerUser(email, password)

        // then
        assertTrue(credentialsManager.registeredUsers.containsKey(email))
    }

    @Test
    fun shouldThrowExceptionForInvalidCredentials() {
        // given
        val email = "invalid-email"
        val password = "1234567"

        // when
        val exception = assertThrows<IllegalArgumentException> {
            credentialsManager.registerUser(email, password)
        }

        // then
        assertTrue(exception.message == "Invalid credentials")
    }

    @Test
    fun shouldThrowExceptionForExistingUser() {
        // given
        val email = "new.user@gmail.com"
        val password = "password123"
        credentialsManager.registeredUsers[email] = password

        // when
        val exception = assertThrows<IllegalArgumentException> {
            credentialsManager.registerUser(email, password)
        }

        // then
        assertTrue(exception.message == "User already exists")
    }
}