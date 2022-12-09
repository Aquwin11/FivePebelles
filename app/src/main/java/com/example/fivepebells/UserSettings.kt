package com.example.fivepebells

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.sqlliteexample.UserModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class UserSettings : AppCompatActivity() {
    lateinit var UserNameTextView: TextView
    lateinit var UserNameEditText: EditText
    lateinit var EditButton: Button
    lateinit var LightThemeButton: Switch
    lateinit var FloatingButton: FloatingActionButton
    lateinit var MuteAudioButton: Switch
    lateinit var AudioSeeker: SeekBar
    lateinit var HomeButton: Button
    lateinit var ExitButton: Button
    lateinit var sqLiteManager: SQLiteManager
    var BufferName = ""
    var CanChange:Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_settings)
        sqLiteManager = SQLiteManager(this)
        HomeButton = findViewById(R.id.MenuHomeButton)
        ExitButton = findViewById(R.id.MenuExitButton)
        UserNameTextView = findViewById(R.id.UserNameHolder)
        UserNameTextView.text = saveName

        UserNameEditText = findViewById(R.id.UserNameEditText)
        FloatingButton = findViewById(R.id.floatingActionButton)
        EditButton = findViewById(R.id.EditUserNameButton)
        EditButton.setOnClickListener {
            if (UserNameTextView.visibility == View.VISIBLE) {

                UserNameTextView.visibility = View.GONE
                UserNameEditText.visibility = View.VISIBLE
                BufferName = UserNameTextView.text.toString()
                EditButton.text = "Save Name"
            } else {
                //saveName = UserNameEditText.text.toString()
                deleteUser()
                addUser(BufferName)
                UserNameTextView.visibility = View.VISIBLE
                UserNameEditText.visibility = View.GONE
                UserNameEditText.text = null
                EditButton.text = "Edit Username"

                Toast.makeText(this, "Saved" + saveName, Toast.LENGTH_SHORT).show()
            }

        }
        AudioSeeker = findViewById(R.id.VolumeSeeker)
        AudioSeeker.progress = VolumeValue
        /*LightThemeButton = findViewById(R.id.LightThemeSwitch)
        LightThemeButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                LightTheme = true
                Toast.makeText(this, "LightModeisTrue", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "DarkModeisTrue", Toast.LENGTH_SHORT).show()
                LightTheme = false
            }
        }


        MuteAudioButton = findViewById(R.id.MuteAudioButton)
        if(MuteAduio)
        {
            MuteAudioButton.isChecked=true
        }



        MuteAudioButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                MuteAduio = true
                //Toast.makeText(this, "LightModeisTrue", Toast.LENGTH_SHORT).show()
                AudioSeeker.isEnabled = true

            } else {
                //Toast.makeText(this, "DarkModeisTrue", Toast.LENGTH_SHORT).show()
                MuteAduio = false
                AudioSeeker.isEnabled = false
                AudioSeeker.progress = VolumeValue
            }
            addUser(BufferName)
            deleteUser()
        }*/

        HomeButton.setOnClickListener {
            val HomeIntent = Intent(this, MainActivity::class.java)
            deleteUser()
            addUser(BufferName)
            startActivity(HomeIntent)
        }
        ExitButton.setOnClickListener {
            deleteUser()
            addUser(BufferName)
            this.finishAffinity();
        }
        FloatingButton.setOnClickListener {
            deleteUser()
            addUser(BufferName)
            super.onBackPressed()
        }
    }


    fun addUser(BufferText : String) {
        val userid = 1
        var name: String = ""
        var string=UserNameEditText.text
        println("string Check ${string.isEmpty()} $CanChange")
        if (!string.isEmpty())
        {
            name = UserNameEditText.text.toString()

        }
        else
        {
            name = UserNameTextView.text.toString()
            println("PRint buffer ${UserNameTextView.text.toString()}")
        }
        val volume = AudioSeeker.progress
        val result = sqLiteManager.insertUser(
            UserModel(
                userid = userid.toString(),
                username = name,
                volumeProgress = volume,
            )
        )
        //clear all edittext s
        println("this should be the result :  $volume , $name")
        UserNameTextView.text = name
        saveName = name
        VolumeValue = volume
    }

    public fun deleteUser() {
        var userid = 1
        val result = sqLiteManager.deleteUser(userid.toString())
        println("result $result")
    }
}
