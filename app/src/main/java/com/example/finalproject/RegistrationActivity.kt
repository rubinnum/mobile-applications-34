package com.example.finalproject

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegistrationActivity : AppCompatActivity() {
    private val login: TextView
        get() = findViewById(R.id.login)

    private val nextButton: Button
        get() = findViewById(R.id.next_button)

    private val validEmailLayout: TextInputLayout
        get() = findViewById(R.id.valid_email_layout)

    private val validEmailEditText: TextInputEditText
        get() = findViewById(R.id.valid_email)

    private val strongPasswordEditText: TextInputEditText
        get() = findViewById(R.id.strong_password)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_activity)

        login.setOnClickListener {
            Utils.displayNewActivity(this, LoginActivity::class.java)
        }

        nextButton.setOnClickListener {
            try {
                val email = validEmailEditText.text.toString()
                val password = strongPasswordEditText.text.toString()

                CredentialsManager.registerUser(email, password)
                Utils.displayNewActivity(this, LoginActivity::class.java)
            } catch (_: IllegalArgumentException) {
                validEmailLayout.error = getString(R.string.invalid_email_message)
            }
        }
    }
}