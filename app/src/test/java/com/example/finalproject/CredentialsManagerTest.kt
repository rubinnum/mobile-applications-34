package com.example.finalproject


import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CredentialsManagerTest {
    @Test
    fun shouldReturnFalseForEmptyEmail() {
        assertFalse(CredentialsManager.isEmailValid(""))
    }

    @Test
    fun shouldReturnFalseForWrongFormatEmail() {
        assertFalse(CredentialsManager.isEmailValid("invalid-email"))
    }

    @Test
    fun shouldReturnTrueForValidEmail() {
        assertTrue(CredentialsManager.isEmailValid("example@email.com"))
    }

    @Test
    fun shouldReturnFalseForEmptyPassword() {
        assertFalse(CredentialsManager.isPasswordValid(""))
    }

    @Test
    fun shouldReturnFalseForPasswordWithLessThanEightChars() {
        assertFalse(CredentialsManager.isPasswordValid("1234567"))
    }

    @Test
    fun shouldReturnTrueForValidPassword() {
        assertTrue(CredentialsManager.isPasswordValid("password123"))
    }

    @Test
    fun shouldReturnTrueForHardcodedCredentials() {
        // given
        val email = "test@te.st"
        val password = "12345678"

        // when
        val result = CredentialsManager.userExists(email, password)

        // then
        assertTrue(result)
    }

    @Test
    fun shouldRegisterUser() {
        // given
        val email = "valid.email@gmail.com"
        val password = "12345678"

        // when
        CredentialsManager.registerUser(email, password)

        // then
        assertTrue(CredentialsManager.registeredUsers.containsKey(email))
    }

    @Test
    fun shouldThrowExceptionForInvalidCredentials() {
        // given
        val email = "invalid-email"
        val password = "1234567"

        // when
        val exception = assertThrows<IllegalArgumentException> {
            CredentialsManager.registerUser(email, password)
        }

        // then
        assertTrue(exception.message == "Invalid credentials")
    }

    @Test
    fun shouldThrowExceptionForExistingUser() {
        // given
        val email = "new.user@gmail.com"
        val password = "12345678"
        CredentialsManager.registeredUsers[email] = password

        // when
        val exception = assertThrows<IllegalArgumentException> {
            CredentialsManager.registerUser(email, password)
        }

        // then
        assertTrue(exception.message == "User already exists")
    }

    @Test
    fun shouldThrowExceptionForExistingUserCaseInsensitive() {
        // given
        val email = "test@te.st"
        val password = "12345678"
        CredentialsManager.registeredUsers[email] = password

        // when
        val exception = assertThrows<IllegalArgumentException> {
            CredentialsManager.registerUser("TEST@te.st", password)
        }

        // then
        assertTrue(exception.message == "User already exists")
    }
}