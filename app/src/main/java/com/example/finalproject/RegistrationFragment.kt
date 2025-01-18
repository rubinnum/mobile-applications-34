package com.example.finalproject

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.finalproject.Utils.Companion.replaceFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegistrationFragment : Fragment() {
    private lateinit var login: TextView
    private lateinit var nextButton: Button
    private lateinit var validEmailLayout: TextInputLayout
    private lateinit var validEmailEditText: TextInputEditText
    private lateinit var strongPasswordEditText: TextInputEditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        login = view.findViewById(R.id.login)
        nextButton = view.findViewById(R.id.next_button)
        validEmailLayout = view.findViewById(R.id.valid_email_layout)
        validEmailEditText = view.findViewById(R.id.valid_email)
        strongPasswordEditText = view.findViewById(R.id.strong_password)

        login.setOnClickListener {
            requireActivity().supportFragmentManager.replaceFragment(
                R.id.fragment_container,
                LoginFragment()
            )
            Utils.displayNewActivity(requireContext(), LoginFragment::class.java)
        }

        nextButton.setOnClickListener {
            try {
                val email = validEmailEditText.text.toString()
                val password = strongPasswordEditText.text.toString()

                CredentialsManager.registerUser(email, password)
                requireActivity().supportFragmentManager.replaceFragment(
                    R.id.fragment_container,
                    LoginFragment.newInstance(email, password)
                )
            } catch (_: IllegalArgumentException) {
                validEmailLayout.error = getString(R.string.invalid_email_message)
            }
        }
    }
}