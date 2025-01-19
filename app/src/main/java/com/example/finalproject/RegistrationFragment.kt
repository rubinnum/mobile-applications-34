package com.example.finalproject

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.finalproject.Utils.Companion.replaceFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegistrationFragment : Fragment(R.layout.registration_fragment) {
    private lateinit var login: TextView
    private lateinit var nextButton: Button
    private lateinit var validEmailLayout: TextInputLayout
    private lateinit var validEmailEditText: TextInputEditText
    private lateinit var strongPasswordEditText: TextInputEditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(view) {
            login = findViewById(R.id.login)
            nextButton = findViewById(R.id.next_button)
            validEmailLayout = findViewById(R.id.valid_email_layout)
            validEmailEditText = findViewById(R.id.valid_email)
            strongPasswordEditText = findViewById(R.id.strong_password)
        }

        login.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        nextButton.setOnClickListener {
            try {
                val email = validEmailEditText.text.toString()
                val password = strongPasswordEditText.text.toString()

                CredentialsManager.registerUser(email, password)
                parentFragmentManager.replaceFragment(
                    R.id.fragment_container,
                    LoginFragment.newInstance(email, password)
                )
            } catch (_: IllegalArgumentException) {
                validEmailLayout.error = getString(R.string.invalid_email_message)
            }
        }
    }
}