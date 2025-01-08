package com.example.finalproject

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RegistrationActivity : AppCompatActivity() {
    private val credentialsManager = CredentialsManager()

    private val login: TextView
        get() = findViewById(R.id.login)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_activity)

        login.setOnClickListener {
            Utils.displayNewActivity(this, LoginActivity::class.java)
        }
    }
}