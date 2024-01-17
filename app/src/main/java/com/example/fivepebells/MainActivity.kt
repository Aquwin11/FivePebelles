package com.example.fivepebells

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView


var saveName:String = ""
var VolumeValue:Int=0
var mediaPlayer: MediaPlayer? = null
object MusicManagerObj{
    fun startMusic(context: Context) {
        println("Start Music Media Player")
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, R.raw.background)
            mediaPlayer?.isLooping = true
            mediaPlayer?.start()
            val newVolume = VolumeValue.toFloat()/100
            //println("volume $newVolume VolumeValue $VolumeValue")
            mediaPlayer?.setVolume(newVolume,newVolume)
        }
    }
    fun pauseMusic() {
        println("Pause Music Media Player")
        try {
            if (mediaPlayer?.isPlaying == true) {
                mediaPlayer?.pause()
            }
        } catch (e: IllegalStateException) {
            Log.e("MusicManagerObj", "Error pausing MediaPlayer", e)
            // Handle or log the exception
        }
    }

    fun resumeMusic() {
        println("Resume Music Media Player")
        try {
            // Resume only if not already playing
            if (mediaPlayer?.isPlaying == false) {
                mediaPlayer?.start()
            }
        } catch (e: IllegalStateException) {
            Log.e("MusicManagerObj", "Error resuming MediaPlayer", e)
            // Handle or log the exception
        }
    }

    fun stopMusic() {
        println("Stop Music Media Player")
        try {
            mediaPlayer?.release() // Release MediaPlayer resources
            mediaPlayer = null
        } catch (e: Exception) {
            Log.e("MusicManagerObj", "Error releasing MediaPlayer", e)
            // Handle or log the exception
        }
    }
}
class MainActivity : AppCompatActivity() {
    lateinit var sqLiteManager: SQLiteManager
    lateinit var UserName:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sqLiteManager = SQLiteManager(this)
        showAllUsers()
        MusicManagerObj.startMusic(this)

        /*mediaPlayer = MediaPlayer.create(this,R.raw.background)
        mediaPlayer.isLooping = true
        mediaPlayer.start()*/
        /*Button button = findViewById (R.id.SinglePlayerButton)
        button.setOnClickListener(new View.OnClickListener)
        {

        }*/
        println("saveName $saveName")
        UserName = findViewById(R.id.UserNameText)
        UserName.text = saveName
        val SinglePlayerbutton = findViewById<Button>(R.id.SinglePlayerButton)
        SinglePlayerbutton.setOnClickListener {
            val Intent = Intent(this, SinglePlayerScreen::class.java)
            startActivity(Intent)


        }
        val Multiplayerbutton = findViewById<Button>(R.id.MultiplayerButton)
        Multiplayerbutton.setOnClickListener {
            val Intent_Multiplayer = Intent(this, Multiplayer::class.java)
            startActivity(Intent_Multiplayer)

        }
        val Rulesbutton = findViewById<Button>(R.id.RulesButton)
        Rulesbutton.setOnClickListener {
            val RulesIntent = Intent(this, RuleScreen::class.java)
            startActivity(RulesIntent)

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
                showAllUsers()
                return true;
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun showAllUsers(){
        val users = sqLiteManager.readAllUsers()
        users.forEach {
            val int = users[0].userid
            saveName = users[0].username
            VolumeValue = users[0].volumeProgress
            println("Just check $saveName , $int,$VolumeValue")
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