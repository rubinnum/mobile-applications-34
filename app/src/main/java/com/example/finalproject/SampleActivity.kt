package com.example.finalproject

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SampleActivity : AppCompatActivity() {
    private val changeFragmentButton: TextView
        get() = findViewById(R.id.change_fragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sample_activity)

        val fragmentA = FragmentA()
        val fragmentB = FragmentB()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragmentA)
            commit()
        }

        changeFragmentButton.setOnClickListener {
            if (fragmentA.isVisible) {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fragment_container, fragmentB)
                    commit()
                    addToBackStack(null);
                }
            } else {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fragment_container, fragmentA)
                    commit()
                }
            }
        }
    }
}