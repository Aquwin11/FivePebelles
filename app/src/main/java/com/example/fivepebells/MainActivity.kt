package com.example.fivepebells

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView


var saveName:String = ""
var LightTheme=false
var MuteAduio = false
var VolumeValue:Int=0
class MainActivity : AppCompatActivity() {
    lateinit var sqLiteManager: SQLiteManager
    lateinit var UserName:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sqLiteManager = SQLiteManager(this)
        showAllUsers()
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
            println("Just check $saveName , $int")
            VolumeValue = users[0].volumeProgress
        }
    }
}