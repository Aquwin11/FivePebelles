package com.example.fivepebells

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


var  PvP=false
class SinglePlayerScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_player_screen)

        val HomeButton = findViewById<Button>(R.id.HomeButton)
        HomeButton.setOnClickListener {
            val HomeIntent = Intent(this, MainActivity::class.java)
            startActivity(HomeIntent)


        }

        val SingleGameButton = findViewById<Button>(R.id.PVP)
        SingleGameButton.setOnClickListener {
            PvP=true
            val gameModeIntent = Intent(this, SinglePlayerGameMode::class.java)
            startActivity(gameModeIntent)


        }

        val SinglePVEGameButton = findViewById<Button>(R.id.PVB)
        SinglePVEGameButton.setOnClickListener {
            PvP=false
            val gameModePVEIntent = Intent(this, SinglePlayerGameMode::class.java)
            startActivity(gameModePVEIntent)


        }
    }
}