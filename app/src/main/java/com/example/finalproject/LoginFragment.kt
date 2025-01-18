package com.example.finalproject

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginFragment : Fragment(R.layout.login_fragment) {
    private lateinit var registerNow: TextView
    private lateinit var emailLayout: TextInputLayout
    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordLayout: TextInputLayout
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var nextButton: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerNow = view.findViewById(R.id.register_now)
        emailLayout = view.findViewById(R.id.email_layout)
        emailEditText = view.findViewById(R.id.email)
        passwordLayout = view.findViewById(R.id.password_layout)
        passwordEditText = view.findViewById(R.id.password)
        nextButton = view.findViewById(R.id.next_button)

        registerNow.setOnClickListener {
            Utils.displayNewActivity(requireContext(), RegistrationFragment::class.java)
        }

        val extraEmail = arguments?.getString("email")
        val extraPassword = arguments?.getString("password")

        emailEditText.setText(extraEmail)
        passwordEditText.setText(extraPassword)

        nextButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            emailLayout.error =
                if (!CredentialsManager.isEmailValid(email)) getString(R.string.invalid_email_message) else null
            passwordLayout.error =
                if (!CredentialsManager.isPasswordValid(password)) getString(R.string.invalid_password_message) else null

            if (CredentialsManager.userExists(email, password)) {
                Utils.displayNewActivity(requireContext(), MainActivity::class.java)
            }
        }
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