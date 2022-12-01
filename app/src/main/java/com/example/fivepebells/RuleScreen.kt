package com.example.fivepebells

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class RuleScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rule_screen)

        val HomeButton = findViewById<Button>(R.id.HomeButton)
        HomeButton.setOnClickListener {
            val HomeIntent = Intent(this, MainActivity::class.java)
            startActivity(HomeIntent)


        }
    }
}