package com.example.finalproject

import android.content.Context
import android.content.Intent

class Utils {
    companion object {
        fun displayNewActivity(context: Context, activity: Class<*>) {
            val intent = Intent(context, activity)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            context.startActivity(intent)
        }
    }
}