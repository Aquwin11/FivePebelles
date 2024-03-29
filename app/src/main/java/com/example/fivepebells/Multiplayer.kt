package com.example.fivepebells

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import android.widget.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue


var codeMaker = true
var code="null"
var codeFound=false
var checkTemp = false
var keyValue :String =  "null"

class Multiplayer : AppCompatActivity() {
    lateinit var loadingProgressBar : ProgressBar
    lateinit var codeET : EditText
    lateinit var  CreateButton : Button
    lateinit var JoinButton : Button
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_multiplayer)
        loadingProgressBar=findViewById(R.id.MultiplePlayerProgressBar)
        JoinButton = findViewById<Button>(R.id.JoinRoom)
        codeET = findViewById(R.id.CreateRoomTextView)
        CreateButton = findViewById(R.id.CreateRoom)

        val HomeButton = findViewById<ImageButton>(R.id.HomeButton)
        HomeButton.setOnClickListener {
            super.onBackPressed()
        }
        /* val CreateRoomDialog = findViewById<Button>(R.id.CreateRoom)
        CreateRoomDialog.setOnClickListener{
            val OpenCreateRoomDialog = layoutInflater.inflate(R.layout.layout_dialog_box,null)
            val myDialog = Dialog(this)
            myDialog.setContentView(OpenCreateRoomDialog)
            myDialog.setCancelable(true)
            myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
            myDialog.show()
        }

        val JoinRoomDialog = findViewById<Button>(R.id.JoinRoom)
        JoinRoomDialog.setOnClickListener{
            val OpenJoinRoomDialog = layoutInflater.inflate(R.layout.layout_dialog_box_two,null)
            val myDialog = Dialog(this)
            myDialog.setContentView(OpenJoinRoomDialog)
            myDialog.setCancelable(true)
            myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
            myDialog.show()
        }*/

        CreateButton.setOnClickListener{
            code = "null"
            codeFound=false
            checkTemp=true
            keyValue="null"
            code = codeET.text.toString()
            CreateButton.visibility = View.GONE
            JoinButton.visibility = View.GONE
            codeET.visibility = View.GONE
            loadingProgressBar.visibility = View.VISIBLE
            if(code!=null && code!="")
            {
                codeMaker=true
                FirebaseDatabase.getInstance().reference.child("codes").addValueEventListener(object :
                    ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        var check = isValueAvailable(snapshot, code)
                        Handler().postDelayed({
                            if(check)
                            {
                                CreateButton.visibility = View.VISIBLE
                                JoinButton.visibility = View.VISIBLE
                                codeET.visibility = View.VISIBLE
                                loadingProgressBar.visibility = View.GONE
                                Toast.makeText(this@Multiplayer, "This Room already created", Toast.LENGTH_SHORT).show()

                            }
                            else
                            {
                                codeET.text = null
                                FirebaseDatabase.getInstance().reference.child("codes").push().setValue(code)
                                isValueAvailable(snapshot, code)
                                FirebaseDatabase.getInstance().reference. child(code).child("Player1").push().setValue(
                                    saveName)
                                checkTemp=false
                                Handler().postDelayed({
                                    accepted()
                                    Toast.makeText(this@Multiplayer, "Creating a Room, Please Wait.... ", Toast.LENGTH_SHORT).show()
                                },300)
                            }
                        },2000)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.w("Multiplayer", "Database listener was cancelled, error: ${error.toException()}")
                    }

                })
            }
            else
            {
                CreateButton.visibility = View.VISIBLE
                JoinButton.visibility = View.VISIBLE
                codeET.visibility = View.VISIBLE
                loadingProgressBar.visibility = View.GONE
                Toast.makeText(this@Multiplayer, "Please Enter a Valid Code", Toast.LENGTH_SHORT).show()
            }

        }

        JoinButton.setOnClickListener{
            code = "null"
            codeFound=false
            checkTemp=true
            keyValue="null"
            code = codeET.text.toString()
            //codeET.setText("")
            if(code!=null && code!="")
            {
                CreateButton.visibility = View.GONE
                JoinButton.visibility = View.GONE
                codeET.visibility = View.GONE
                loadingProgressBar.visibility = View.VISIBLE
                codeMaker=false
                FirebaseDatabase.getInstance().reference.child("codes").addValueEventListener(object :
                    ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        var data : Boolean= isValueAvailable(snapshot, code)
                        println("DataCheck" + code)
                        Handler().postDelayed({
                            if(data)
                            {
                                CreateButton.visibility = View.VISIBLE
                                JoinButton.visibility = View.VISIBLE
                                codeET.visibility = View.VISIBLE
                                loadingProgressBar.visibility = View.GONE
                                codeFound=true
                                //JoinButton.visibility = View.INVISIBLE
                                accepted()
                                FirebaseDatabase.getInstance().reference. child(code).child("Player2").push().setValue(
                                    saveName)
                                Toast.makeText(this@Multiplayer, "Please Wait. Joining the Room", Toast.LENGTH_SHORT).show()
                            }
                            else
                            {
                                CreateButton.visibility = View.VISIBLE
                                JoinButton.visibility = View.VISIBLE
                                codeET.visibility = View.VISIBLE
                                loadingProgressBar.visibility = View.GONE
                                Toast.makeText(this@Multiplayer, "Please enter an Existing Room Code", Toast.LENGTH_SHORT).show()
                            }
                        },2000)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                })
            }
            else
            {
                Toast.makeText(this@Multiplayer, "Please Enter a Valid Code", Toast.LENGTH_SHORT).show()
            }
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

    fun accepted()
    {
        startActivity(Intent(this,MultiPlayerGameMode::class.java))
        JoinButton.visibility=View.VISIBLE
        codeET.visibility = View.VISIBLE
        loadingProgressBar.visibility = View.GONE
        CreateButton.visibility= View.VISIBLE
        //close Create Room Popup
    }

    fun isValueAvailable(snapshot: DataSnapshot, code:String):Boolean
    {
        println("check1")
        var data = snapshot.children
        println(data)
        snapshot.children.forEach{
            println("check2")
            var value = it.getValue().toString()
            if(value == code)
            {
                keyValue=it.key.toString()
                return true
            }
        }
        println("check3")
        //isValueAvailable(snapshot,code)
        return false
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
}