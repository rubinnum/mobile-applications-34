package com.example.finalproject

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.finalproject.Utils.Companion.replaceFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch

class LoginFragment : Fragment(R.layout.login_fragment) {
    private lateinit var registerNow: TextView
    private lateinit var emailLayout: TextInputLayout
    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordLayout: TextInputLayout
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var nextButton: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
        setupClickListeners()
        setInitialCredentials()
        observeLoginState()
    }

    private fun setupViews(view: View) {
        with(view) {
            registerNow = findViewById(R.id.register_now)
            emailLayout = findViewById(R.id.email_layout)
            emailEditText = findViewById(R.id.email)
            passwordLayout = findViewById(R.id.password_layout)
            passwordEditText = findViewById(R.id.password)
            nextButton = findViewById(R.id.next_button)
        }
    }

    private fun setupClickListeners() {
        registerNow.setOnClickListener {
            navigateToRegistration()
        }

        nextButton.setOnClickListener {
            handleLoginAttempt()
        }
    }

    private fun setInitialCredentials() {
        arguments?.let { args ->
            emailEditText.setText(args.getString("email"))
            passwordEditText.setText(args.getString("password"))
        }
    }

    private fun observeLoginState() {
        viewLifecycleOwner.lifecycleScope.launch {
            CredentialsManager.loginState.collect { state ->
                when (state) {
                    is CredentialsManager.LoginState.LoggedIn -> navigateToRecipes()
                    else -> {}
                }
            }
        }
    }

    private fun handleLoginAttempt() {
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        validateAndShowErrors(email, password)
        CredentialsManager.login(email, password)
    }

    private fun validateAndShowErrors(email: String, password: String) {
        emailLayout.error = if (!CredentialsManager.isEmailValid(email)) {
            getString(R.string.invalid_email_message)
        } else null

        passwordLayout.error = if (!CredentialsManager.isPasswordValid(password)) {
            getString(R.string.invalid_password_message)
        } else null
    }

    private fun navigateToRecipes() {
        parentFragmentManager.replaceFragment(
            R.id.fragment_container,
            RecipesFragment(),
            false
        )
    }

    private fun navigateToRegistration() {
        parentFragmentManager.replaceFragment(
            R.id.fragment_container,
            RegistrationFragment(),
            true
        )
    }

    companion object {
        fun newInstance(email: String, password: String): LoginFragment {
            val fragment = LoginFragment()
            val args = Bundle()
            args.putString("email", email)
            args.putString("password", password)
            fragment.arguments = args
            return fragment
        }
    }
}