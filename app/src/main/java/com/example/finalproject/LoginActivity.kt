package com.example.finalproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.login_activity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val registerNow = findViewById<TextView>(R.id.register_now)
        registerNow.setOnClickListener {
            displayNewActivity(RegistrationActivity::class.java)
        }

        val credentialsManager = CredentialsManager()
        val emailLayout = findViewById<TextInputLayout>(R.id.email_layout)
        val emailEditText = findViewById<TextInputEditText>(R.id.email)
        val passwordLayout = findViewById<TextInputLayout>(R.id.password_layout)
        val passwordEditText = findViewById<TextInputEditText>(R.id.password)
        val nextButton = findViewById<Button>(R.id.next_button)

        nextButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            emailLayout.error = if (!credentialsManager.isEmailValid(email)) getString(R.string.invalid_email_message) else null
            passwordLayout.error = if (!credentialsManager.isPasswordValid(password)) getString(R.string.invalid_password_message) else null

            if (credentialsManager.userExists(email, password)) {
                displayNewActivity(MainActivity::class.java)
            }
        }
    }

    private fun displayNewActivity(cls: Class<*>) {
        val intent = Intent(this, cls)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(intent)
    }
}