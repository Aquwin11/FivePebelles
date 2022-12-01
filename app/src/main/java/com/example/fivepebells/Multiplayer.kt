package com.example.fivepebells

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.PopupMenu

class Multiplayer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiplayer)

        val HomeButton = findViewById<Button>(R.id.HomeButton)
        HomeButton.setOnClickListener {
            val HomeIntent = Intent(this, MainActivity::class.java)
            startActivity(HomeIntent)
        }
    }
}