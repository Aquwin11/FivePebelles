package com.example.fivepebells

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton

class RuleScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rule_screen)

        val HomeButton = findViewById<ImageButton>(R.id.HomeButton)
        HomeButton.setOnClickListener {
            super.onBackPressed()
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
}