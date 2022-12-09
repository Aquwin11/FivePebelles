package com.example.fivepebells

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.widget.*
import androidx.core.os.postDelayed
import androidx.core.view.isInvisible
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize



class LayoutDialogBox : AppCompatActivity() {

    lateinit var UserNameTextView:TextView
    lateinit var UserNameEditText:EditText
    lateinit var EditButton:Button
    lateinit var Lighttheme:Switch
    lateinit var MuteAudioButton : Switch
    lateinit var AudioSeeker:SeekBar
    lateinit var HomeButton : Button
    lateinit var ExitButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        //WidgetsFlutterBinding.ensureInitialized()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_dialog_box)

        UserNameTextView=findViewById(R.id.UserNameHolder)
        UserNameEditText=findViewById(R.id.UserNameEditText)
        EditButton = findViewById(R.id.EditUserNameButton)
        EditButton.setOnClickListener{
            if(UserNameTextView.visibility==View.VISIBLE)
            {
                UserNameTextView.visibility=View.GONE
                UserNameEditText.visibility=View.VISIBLE
                EditButton.text = "Save Name"
            }
            else{
                UserNameTextView.visibility=View.VISIBLE
                UserNameEditText.visibility=View.GONE
                EditButton.text = "Edit Username"
            }
        }
        /*HomeButton = findViewById(R.id.MenuHomeButton)
        HomeButton.setOnClickListener{
            Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show()
        }*/
    }


}