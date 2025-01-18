package com.example.finalproject

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.finalproject.Utils.Companion.replaceFragment

class SampleActivity : AppCompatActivity() {
    private val changeFragmentButton: TextView
        get() = findViewById(R.id.change_fragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sample_activity)

        val fragmentA = FragmentA()
        val fragmentB = FragmentB()

        supportFragmentManager.replaceFragment(R.id.fragment_container, fragmentA)

        changeFragmentButton.setOnClickListener {
            if (fragmentA.isVisible)
                supportFragmentManager.replaceFragment(R.id.fragment_container, fragmentB, true)
            else
                supportFragmentManager.replaceFragment(R.id.fragment_container, fragmentA)
        }
    }
}