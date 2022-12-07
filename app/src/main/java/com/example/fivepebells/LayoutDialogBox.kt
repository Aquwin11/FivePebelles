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


    override fun onCreate(savedInstanceState: Bundle?) {
        //WidgetsFlutterBinding.ensureInitialized()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_dialog_box)


    }

    public fun menuExitclick(view: View)
    {

    }

}