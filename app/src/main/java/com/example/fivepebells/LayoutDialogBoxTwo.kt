package com.example.fivepebells

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LayoutDialogBoxTwo : AppCompatActivity() {/*

    lateinit var InputField: EditText
    lateinit var JoinRoomBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        InputField = findViewById(R.id.JoinRoomEditText)
        JoinRoomBtn = findViewById(R.id.JoinRoomBtn)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_dialog_box_two)




        JoinRoomBtn.setOnClickListener{

            code = "null"
            codeFound=false
            checkTemp=true
            keyValue="null"
            code = InputField.text.toString()
            InputField.setText("")
            if(code!=null && code!="")
            {
                codeMaker=false
                FirebaseDatabase.getInstance().reference.child("codes").addValueEventListener(object :
                    ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        var data = isValueAvailable(snapshot, code)
                        Handler().postDelayed({
                            if(data)
                            {
                                codeFound=true
                                JoinRoomBtn.visibility = View.INVISIBLE
                                accepted()
                                Toast.makeText(this@LayoutDialogBoxTwo, "Please Wait. Joining the Room", Toast.LENGTH_SHORT).show()
                            }
                            else
                            {
                                Toast.makeText(this@LayoutDialogBoxTwo, "Please enter an Existing Room Code", Toast.LENGTH_SHORT).show()
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
                Toast.makeText(this@LayoutDialogBoxTwo, "Please Enter a Valid Code", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun accepted()
    {
        startActivity(Intent(this,MultiPlayerGameMode::class.java))
        JoinRoomBtn.visibility= View.INVISIBLE
        //close Create Room Popup
    }

    fun isValueAvailable(snapshot: DataSnapshot, code:String):Boolean
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
