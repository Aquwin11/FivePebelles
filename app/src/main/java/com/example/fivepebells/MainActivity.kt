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

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*Button button = findViewById (R.id.SinglePlayerButton)
        button.setOnClickListener(new View.OnClickListener)
        {

        }*/
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

                    val OpenCreateRoomDialog = layoutInflater.inflate(R.layout.layout_dialog_box, null)
                    val myDialog = Dialog(this)
                    myDialog.setContentView(OpenCreateRoomDialog)
                    myDialog.setCancelable(true)
                    myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
                    myDialog.show()
                return true;
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}