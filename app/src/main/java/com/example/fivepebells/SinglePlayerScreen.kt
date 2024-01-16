package com.example.fivepebells

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton


var  PvP=false
class SinglePlayerScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_player_screen)

        val HomeButton = findViewById<ImageButton>(R.id.HomeButton)
        HomeButton.setOnClickListener {
            super.onBackPressed()
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
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuButton -> {

                val SettingsIntent = Intent(this, UserSettings::class.java)
                startActivity(SettingsIntent)
                return true;
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
    override fun onPause() {
        super.onPause()
        // Pause music when the activity is not in the foreground
        MusicManagerObj.pauseMusic()
    }

    override fun onResume() {
        super.onResume()
        // Resume music playback when the activity comes back to the foreground
        MusicManagerObj.resumeMusic()
    }

    /*override fun onDestroy() {
        super.onDestroy()
        // Release the media player resources when the activity is destroyed
        MusicManagerObj.stopMusic()
    }*/
}