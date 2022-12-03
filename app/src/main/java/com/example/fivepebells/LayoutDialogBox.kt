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


/*var codeMaker = true
var code="null"
var codeFound=false
var checkTemp = false
var keyValue :String =  "null"*/

class LayoutDialogBox : AppCompatActivity() {/*


    lateinit var InputField:EditText
    lateinit var CreateRoomBtn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        println("Check1")
        //WidgetsFlutterBinding.ensureInitialized()
        FirebaseApp.initializeApp(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_dialog_box)



        InputField = findViewById<EditText>(R.id.CreateRoomTextView)
        CreateRoomBtn = findViewById<Button>(R.id.CreateRoomBtn)
        CreateRoomBtn.setOnClickListener{
            code = "null"
            codeFound=false
            checkTemp=true
            keyValue="null"
            code = InputField.text.toString()
            if(code!=null && code!="")
            {
                codeMaker=true
                FirebaseDatabase.getInstance().reference.child("code").addValueEventListener(object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        var check = isValueAvailable(snapshot, code)
                        Handler().postDelayed({
                            if(check)
                            {

                            }
                            else
                            {
                                FirebaseDatabase.getInstance().reference.child("code").push().setValue(code)
                                isValueAvailable(snapshot, code)
                                checkTemp=false
                                Handler().postDelayed({
                                    accepted()
                                    Toast.makeText(this@LayoutDialogBox, "Creating a Room, Please Wait.... ", Toast.LENGTH_SHORT).show()
                                },300)
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
                Toast.makeText(this@LayoutDialogBox, "Please Enter a Valid Code", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun accepted()
    {
        startActivity(Intent(this,MultiPlayerGameMode::class.java))
        CreateRoomBtn.visibility= View.INVISIBLE
        //close Create Room Popup
    }

    fun isValueAvailable(snapshot: DataSnapshot,code:String):Boolean
    {
        var data = snapshot.children
        data.forEach{
            var value = it.getValue().toString()
            if(value == code)
            {
                keyValue=it.key.toString()
                return true
            }
        }
        return false
    }*/

}