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
        setupViews(view)
        setupLoginClickListener()
        setupNextButtonClickListener()
    }

    private fun setupViews(view: View) {
        with(view) {
            login = findViewById(R.id.login)
            nextButton = findViewById(R.id.next_button)
            validEmailLayout = findViewById(R.id.valid_email_layout)
            validEmailEditText = findViewById(R.id.valid_email)
            strongPasswordEditText = findViewById(R.id.strong_password)
        }
    }

    private fun setupLoginClickListener() {
        login.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun setupNextButtonClickListener() {
        nextButton.setOnClickListener {
            handleRegistration()
        }
    }

    private fun handleRegistration() {
        try {
            val email = validEmailEditText.text.toString()
            val password = strongPasswordEditText.text.toString()

            CredentialsManager.registerUser(email, password)
            navigateToLoginFragment(email, password)
        } catch (_: IllegalArgumentException) {
            validEmailLayout.error = getString(R.string.invalid_email_message)
        }
    }

    private fun navigateToLoginFragment(email: String, password: String) {
        parentFragmentManager.replaceFragment(
            R.id.fragment_container,
            LoginFragment.newInstance(email, password)
        )
    }
}
