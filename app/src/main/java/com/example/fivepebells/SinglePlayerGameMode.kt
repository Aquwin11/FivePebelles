package com.example.fivepebells

import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.net.IpSecManager.ResourceUnavailableException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.os.postDelayed
import kotlin.system.exitProcess


var playTurn=true
class SinglePlayerGameMode : AppCompatActivity() {
    lateinit var player1TV:TextView
    lateinit var player2TV:TextView
    lateinit var BoxBtn1:Button
    lateinit var BoxBtn2:Button
    lateinit var BoxBtn3:Button
    lateinit var BoxBtn4:Button
    lateinit var BoxBtn5:Button
    lateinit var BoxBtn6:Button
    lateinit var BoxBtn7:Button
    lateinit var BoxBtn8:Button
    lateinit var BoxBtn9:Button
    lateinit var BoxBtn10:Button
    lateinit var BoxBtn11:Button
    lateinit var BoxBtn12:Button
    lateinit var BoxBtn13:Button
    lateinit var BoxBtn14:Button
    lateinit var BoxBtn15:Button
    lateinit var BoxBtn16:Button
    lateinit var BoxBtn17:Button
    lateinit var BoxBtn18:Button
    lateinit var BoxBtn19:Button
    lateinit var BoxBtn20:Button
    lateinit var BoxBtn21:Button
    lateinit var BoxBtn22:Button
    lateinit var BoxBtn23:Button
    lateinit var BoxBtn24:Button
    lateinit var BoxBtn25:Button
    lateinit var BoxBtn26:Button
    lateinit var BoxBtn27:Button
    lateinit var BoxBtn28:Button
    lateinit var BoxBtn29:Button
    lateinit var BoxBtn30:Button
    lateinit var BoxBtn31:Button
    lateinit var BoxBtn32:Button
    lateinit var BoxBtn33:Button
    lateinit var BoxBtn34:Button
    lateinit var BoxBtn35:Button
    lateinit var BoxBtn36:Button
    lateinit var Player1Switch:Button
    lateinit var Player2Switch:Button
    lateinit var SwitchCounter:TextView
    lateinit var Reset:Button
    lateinit var Player1Turn:RadioButton
    lateinit var Player2Turn:RadioButton
    lateinit var player1Value:TextView
    lateinit var player2Value:TextView


    var player1count=0
    var player2count=0
    var counterCount=1

    var player1=ArrayList<Int>()
    var player2=ArrayList<Int>()
    var emptyCell=ArrayList<Int>()
    var activeUser =1



    //Advance bot test
    var WinState=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_player_game_mode)
        player1TV=findViewById(R.id.Player1Text)
        player2TV=findViewById(R.id.Player2Text)
        player2Value=findViewById(R.id.Player2Character)
        player1Value=findViewById(R.id.Player1Character)
        Reset=findViewById(R.id.SingleResetButton)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        BoxBtn1=findViewById(R.id.Box1)
        BoxBtn1=findViewById(R.id.Box1)
        BoxBtn2=findViewById(R.id.Box2)
        BoxBtn3=findViewById(R.id.Box3)
        BoxBtn4=findViewById(R.id.Box4)
        BoxBtn5=findViewById(R.id.Box5)
        BoxBtn6=findViewById(R.id.Box6)
        BoxBtn7=findViewById(R.id.Box7)
        BoxBtn8=findViewById(R.id.Box8)
        BoxBtn9=findViewById(R.id.Box9)
        BoxBtn10=findViewById(R.id.Box10)
        BoxBtn11=findViewById(R.id.Box11)
        BoxBtn12=findViewById(R.id.Box12)
        BoxBtn13=findViewById(R.id.Box13)
        BoxBtn14=findViewById(R.id.Box14)
        BoxBtn15=findViewById(R.id.Box15)
        BoxBtn16=findViewById(R.id.Box16)
        BoxBtn17=findViewById(R.id.Box17)
        BoxBtn18=findViewById(R.id.Box18)
        BoxBtn19=findViewById(R.id.Box19)
        BoxBtn20=findViewById(R.id.Box20)
        BoxBtn21=findViewById(R.id.Box21)
        BoxBtn22=findViewById(R.id.Box22)
        BoxBtn23=findViewById(R.id.Box23)
        BoxBtn24=findViewById(R.id.Box24)
        BoxBtn25=findViewById(R.id.Box25)
        BoxBtn26=findViewById(R.id.Box26)
        BoxBtn27=findViewById(R.id.Box27)
        BoxBtn28=findViewById(R.id.Box28)
        BoxBtn29=findViewById(R.id.Box29)
        BoxBtn30=findViewById(R.id.Box30)
        BoxBtn31=findViewById(R.id.Box31)
        BoxBtn32=findViewById(R.id.Box32)
        BoxBtn33=findViewById(R.id.Box33)
        BoxBtn34=findViewById(R.id.Box34)
        BoxBtn35=findViewById(R.id.Box35)
        BoxBtn36=findViewById(R.id.Box36)
        Player1Turn = findViewById(R.id.player1RadioButton)
        Player2Turn = findViewById(R.id.player2RadioButton)
        Player1Turn.isChecked=true
        Reset.setOnClickListener{
            reset()
        }

        SwitchCounter = findViewById(R.id.SwitchCounter)
        Player1Switch=findViewById(R.id.SinglePlayerSwitchButtonPlayer1)
        Player1Switch.isEnabled=false
        Player1Switch.setOnClickListener{
            switch()
        }
        Player2Switch=findViewById(R.id.SinglePlayerSwitchButtonPlayer2)
        Player2Switch.isEnabled=false
        Player2Switch.setOnClickListener{
            switch()
        }
        if(!PvP)
        {
            Player2Switch.visibility=View.INVISIBLE
            Player2Switch.isEnabled=false
        }

/*        val HomeButton = findViewById<Button>(R.id.HomeButton)
        HomeButton.setOnClickListener {
            val HomeIntent = Intent(this, MainActivity::class.java)
            startActivity(HomeIntent)
        }*/
        val HomeButton = findViewById<ImageButton>(R.id.HomeButton)
        HomeButton.setOnClickListener {
            super.onBackPressed()
        }

    }

    private fun switch(){
        Player2Switch.isEnabled=false
        Player1Switch.isEnabled=false
        counterCount++
        SwitchCounter.text="5"
        val NewString = SwitchCounter.text
        var NewInt = Integer.parseInt(NewString as String)
        SwitchCounter.text = (NewInt*counterCount).toString()
        println("Before ActivePlayer " + activeUser)
        var templist1 = ArrayList<Int>()
        var templist2 = ArrayList<Int>()
        templist1=player1
        templist2=player2
        player1=templist2
        player2=templist1
        if(player1Value.text=="X")
        {
            player1Value.text="O"
            player2Value.text="X"

        }
        else
        {
            player1Value.text="X"
            player2Value.text="O"
        }



        if(activeUser==1)
        {
            if(PvP)
            {
                activeUser=2
                Player1Turn.isChecked=false
                Player2Turn.isChecked=true

            }
            else
            {
                /*Player1Turn.isChecked=false
                Player2Turn.isChecked=true*/
                robot()
            }

        }
        else
        {
            Player1Turn.isChecked=true
            Player2Turn.isChecked=false
            activeUser=1
        }
        println("After ActivePlayer " + activeUser)

    }

    fun buttonClick(view: View){
        //println("The button click")
        if(playTurn)
        {
            val but =view as Button
            var cellID=0
            when(but.id)
            {
                R.id.Box1->cellID=1
                R.id.Box2->cellID=2
                R.id.Box3->cellID=3
                R.id.Box4->cellID=4
                R.id.Box5->cellID=5
                R.id.Box6->cellID=6
                R.id.Box7->cellID=7
                R.id.Box8->cellID=8
                R.id.Box9->cellID=9
                R.id.Box10->cellID=10
                R.id.Box11->cellID=11
                R.id.Box12->cellID=12
                R.id.Box13->cellID=13
                R.id.Box14->cellID=14
                R.id.Box15->cellID=15
                R.id.Box16->cellID=16
                R.id.Box17->cellID=17
                R.id.Box18->cellID=18
                R.id.Box19->cellID=19
                R.id.Box20->cellID=20
                R.id.Box21->cellID=21
                R.id.Box22->cellID=22
                R.id.Box23->cellID=23
                R.id.Box24->cellID=24
                R.id.Box25->cellID=25
                R.id.Box26->cellID=26
                R.id.Box27->cellID=27
                R.id.Box28->cellID=28
                R.id.Box29->cellID=29
                R.id.Box30->cellID=30
                R.id.Box31->cellID=31
                R.id.Box32->cellID=32
                R.id.Box33->cellID=33
                R.id.Box34->cellID=34
                R.id.Box35->cellID=35
                R.id.Box36->cellID=36
            }
            playTurn=false
            Handler().postDelayed(Runnable { playTurn=true },700)
            playNow(but,cellID)


        }

    }

    fun playNow(buttonSelected: Button, currentCell: Int) {
        println("ActivePlayer " + activeUser)
        val NewString = SwitchCounter.text
        var NewInt = Integer.parseInt(NewString as String)
        println("New Integer" + NewInt)
        if(NewInt>0)
        {
            if(PvP)
            {
                NewInt-=1
            }
            else
            {
                NewInt-=2
            }
        }
        if (NewInt<0)
        {
            NewInt=0
        }
        SwitchCounter.text = NewInt.toString()
        if(activeUser==1)
        {
            Player1Turn.isChecked=false
            Player2Turn.isChecked=true
            if(NewInt<=0)
            {
                Player2Switch.isEnabled=true
                Player1Switch.isEnabled=false

            }
            else
            {
                Player2Switch.isEnabled=false
                Player1Switch.isEnabled=false
            }
            if(player1Value.text=="X")
            {
                buttonSelected.text="X"
                buttonSelected.setTextColor(Color.parseColor("#D22BB804"))

            }
            else
            {
                buttonSelected.text="O"
                buttonSelected.setTextColor(Color.parseColor("#EC0C0C"))
            }

            player1.add(currentCell)
            emptyCell.add(currentCell)
            buttonSelected.isEnabled=false
            //Handler().postDelayed(Runnable {  })
            val checkWinner=checkForWinner()
            if(checkWinner==1)
            {
                Handler().postDelayed(Runnable { reset() },2000)

            }else if(!PvP)
            {
                if(NewInt<=0)
                {
                    Player1Switch.isEnabled=true

                }
                else
                {
                    Player1Switch.isEnabled=false
                }
                Handler().postDelayed(Runnable { robot() },500)

            }
            else
            {
                activeUser=2
            }

        }
        else
        {
            Player1Turn.isChecked=true
            Player2Turn.isChecked=false
            if(NewInt==0)
            {
                Player2Switch.isEnabled=false
                Player1Switch.isEnabled=true

            }
            else
            {
                Player2Switch.isEnabled=false
                Player1Switch.isEnabled=false
            }
            if(player1Value.text=="X")
            {
                buttonSelected.text="O"
                buttonSelected.setTextColor(Color.parseColor("#EC0C0C"))
            }
            else
            {
                buttonSelected.text="X"
                buttonSelected.setTextColor(Color.parseColor("#D22BB804"))
            }
            activeUser=1
            player2.add(currentCell)
            emptyCell.add(currentCell)
            buttonSelected.isEnabled=false
            //Handler().postDelayed(Runnable {  })
            val checkWinner=checkForWinner()
            if(checkWinner==1)
            {
                Handler().postDelayed(Runnable { reset() },2000)

            }
        }


    }

    private fun robot() {

        val rnd =(1..36).random()
        if(emptyCell.contains(rnd))
        {
            robot()
        }
        else{
            val buttonSelected=when(rnd){
                1 -> BoxBtn1
                2->BoxBtn2
                3->BoxBtn3
                4->BoxBtn4
                5->BoxBtn5
                6->BoxBtn6
                7->BoxBtn7
                8->BoxBtn8
                9->BoxBtn9
                10->BoxBtn10
                11->BoxBtn11
                12->BoxBtn12
                13->BoxBtn13
                14->BoxBtn14
                15->BoxBtn15
                16->BoxBtn16
                17->BoxBtn17
                18->BoxBtn18
                19->BoxBtn19
                20->BoxBtn20
                21->BoxBtn21
                22->BoxBtn22
                23->BoxBtn23
                24->BoxBtn24
                25->BoxBtn25
                26->BoxBtn26
                27->BoxBtn27
                28->BoxBtn28
                29->BoxBtn29
                30->BoxBtn30
                31->BoxBtn31
                32->BoxBtn32
                33->BoxBtn33
                34->BoxBtn34
                35->BoxBtn35
                36->BoxBtn36
                else->
                {
                    BoxBtn1
                }
            }
            emptyCell.add(rnd)

            if(player2Value.text=="X")
            {
                buttonSelected.text="X"
                buttonSelected.setTextColor(Color.parseColor("#D22BB804"))
            }
            else
            {
                buttonSelected.text="O"
                buttonSelected.setTextColor((Color.parseColor("#EC0C0C")))
            }
            player2.add(rnd)
            buttonSelected.isEnabled=false
            var checkWinner=checkForWinner()

            if(checkWinner==1)
            {
                Handler().postDelayed({reset()},500)
            }
        }
        Player1Turn.isChecked=true
        Player2Turn.isChecked=false
    }



    private fun checkForWinner(): Int {
    if((player1.contains(1) && player1.contains(2) && player1.contains(3) && player1.contains(4))||(player1.contains(2) && player1.contains(3) && player1.contains(4) && player1.contains(5) || player1.contains(3) && player1.contains(4) && player1.contains(5) && player1.contains(6))
        || player1.contains(7) && player1.contains(8) && player1.contains(9) && player1.contains(10)|| player1.contains(8) && player1.contains(9) && player1.contains(10) && player1.contains(11) || player1.contains(9) && player1.contains(10) && player1.contains(11) && player1.contains(12)
        || player1.contains(13) && player1.contains(14) && player1.contains(15) && player1.contains(16) || player1.contains(14) && player1.contains(15) && player1.contains(16) && player1.contains(17) || player1.contains(15) && player1.contains(16) && player1.contains(17) && player1.contains(18)
        || player1.contains(19) && player1.contains(20) && player1.contains(21) && player1.contains(22) || player1.contains(21) && player1.contains(22) && player1.contains(23) && player1.contains(20) || player1.contains(21) && player1.contains(22) && player1.contains(23) && player1.contains(24)
        || player1.contains(25) && player1.contains(26) && player1.contains(27) && player1.contains(28) || player1.contains(26) && player1.contains(27) && player1.contains(28) && player1.contains(29) || player1.contains(30) && player1.contains(27) && player1.contains(28) && player1.contains(29)
        || player1.contains(31) && player1.contains(32) && player1.contains(33) && player1.contains(34) || player1.contains(32) && player1.contains(33) && player1.contains(34) && player1.contains(35) || player1.contains(36) && player1.contains(33) && player1.contains(34) && player1.contains(35)
        || player1.contains(1) && player1.contains(7) && player1.contains(13) && player1.contains(19) || player1.contains(31) && player1.contains(13) && player1.contains(19) && player1.contains(25) || player1.contains(7) && player1.contains(13) && player1.contains(19) && player1.contains(25)
        || player1.contains(2) && player1.contains(8) && player1.contains(14) && player1.contains(20) || player1.contains(8) && player1.contains(14) && player1.contains(20) && player1.contains(26) || player1.contains(32) && player1.contains(14) && player1.contains(20) && player1.contains(26)
        || player1.contains(3) && player1.contains(9) && player1.contains(15) && player1.contains(21) || player1.contains(9) && player1.contains(15) && player1.contains(21) && player1.contains(27) || player1.contains(33) && player1.contains(15) && player1.contains(21) && player1.contains(27)
        || player1.contains(4) && player1.contains(10) && player1.contains(16) && player1.contains(22) || player1.contains(10) && player1.contains(16) && player1.contains(22) && player1.contains(28) || player1.contains(34) && player1.contains(16) && player1.contains(22) && player1.contains(28)
        || player1.contains(5) && player1.contains(11) && player1.contains(17) && player1.contains(23) || player1.contains(11) && player1.contains(17) && player1.contains(23) && player1.contains(29) || player1.contains(35) && player1.contains(17) && player1.contains(23) && player1.contains(29)
        || player1.contains(6) && player1.contains(12) && player1.contains(18) && player1.contains(24) || player1.contains(12) && player1.contains(18) && player1.contains(24) && player1.contains(30) || player1.contains(36) && player1.contains(18) && player1.contains(24) && player1.contains(30)
        || player1.contains(1) && player1.contains(8) && player1.contains(15) && player1.contains(22)  || player1.contains(8) && player1.contains(15) && player1.contains(22) && player1.contains(29) || player1.contains(36) && player1.contains(15) && player1.contains(22) && player1.contains(29)
        || player1.contains(6) && player1.contains(11) && player1.contains(16) && player1.contains(21) || player1.contains(11) && player1.contains(16) && player1.contains(21) && player1.contains(26) || player1.contains(31) && player1.contains(16) && player1.contains(21) && player1.contains(26)
        || player1.contains(2) && player1.contains(9) && player1.contains(16) && player1.contains(23) || player1.contains(30) && player1.contains(9) && player1.contains(16) && player1.contains(23)  || player1.contains(14) && player1.contains(21) && player1.contains(28) && player1.contains(35) || player1.contains(7) && player1.contains(14) && player1.contains(21) && player1.contains(28)
        || player1.contains(5) && player1.contains(10) && player1.contains(15) && player1.contains(20)|| player1.contains(10) && player1.contains(15) && player1.contains(20) && player1.contains(25) || player1.contains(12) && player1.contains(17) && player1.contains(22) && player1.contains(27) || player1.contains(17) && player1.contains(22) && player1.contains(27) && player1.contains(32)
        || player1.contains(3) && player1.contains(20) && player1.contains(27) && player1.contains(34)|| player1.contains(18) && player1.contains(23) && player1.contains(28) && player1.contains(33) || player1.contains(4) && player1.contains(9) && player1.contains(14) && player1.contains(19) || player1.contains(3) && player1.contains(10) && player1.contains(17) && player1.contains(24))
    {
        player1count+=1
        buttonDisable()
        disableReset()
        val build = AlertDialog.Builder(this)
        build.setTitle("Game Over")
        build.setMessage("Player 1 Wins \n \n " + "Do you want to play again")
        build.setPositiveButton("ok"){
            dialog,which->
            reset()

        }
        build.setNegativeButton("Exit"){
            dialog,which->
            exitProcess(1)
        }
        Handler().postDelayed(Runnable { build.show() },200)
        println("Player1 won the game")
        return 1

    }
        else if((player2.contains(1) && player2.contains(2) && player2.contains(3) && player2.contains(4))||(player2.contains(2) && player2.contains(3) && player2.contains(4) && player2.contains(5) || player2.contains(3) && player2.contains(4) && player2.contains(5) && player2.contains(6))
        || player2.contains(7) && player2.contains(8) && player2.contains(9) && player2.contains(10)|| player2.contains(8) && player2.contains(9) && player2.contains(10) && player2.contains(11) || player2.contains(9) && player2.contains(10) && player2.contains(11) && player2.contains(12)
        || player2.contains(13) && player2.contains(14) && player2.contains(15) && player2.contains(16) || player2.contains(14) && player2.contains(15) && player2.contains(16) && player2.contains(17) || player2.contains(15) && player2.contains(16) && player2.contains(17) && player2.contains(18)
        || player2.contains(19) && player2.contains(20) && player2.contains(21) && player2.contains(22) || player2.contains(21) && player2.contains(22) && player2.contains(23) && player2.contains(20) || player2.contains(21) && player2.contains(22) && player2.contains(23) && player2.contains(24)
        || player2.contains(25) && player2.contains(26) && player2.contains(27) && player2.contains(28) || player2.contains(26) && player2.contains(27) && player2.contains(28) && player2.contains(29) || player2.contains(30) && player2.contains(27) && player2.contains(28) && player2.contains(29)
        || player2.contains(31) && player2.contains(32) && player2.contains(33) && player2.contains(34) || player2.contains(32) && player2.contains(33) && player2.contains(34) && player2.contains(35) || player2.contains(36) && player2.contains(33) && player2.contains(34) && player2.contains(35)
        || player2.contains(1) && player2.contains(7) && player2.contains(13) && player2.contains(19) || player2.contains(31) && player2.contains(13) && player2.contains(19) && player2.contains(25) || player2.contains(7) && player2.contains(13) && player2.contains(19) && player2.contains(25)
        || player2.contains(2) && player2.contains(8) && player2.contains(14) && player2.contains(20) || player2.contains(8) && player2.contains(14) && player2.contains(20) && player2.contains(26) || player2.contains(32) && player2.contains(14) && player2.contains(20) && player2.contains(26)
        || player2.contains(3) && player2.contains(9) && player2.contains(15) && player2.contains(21) || player2.contains(9) && player2.contains(15) && player2.contains(21) && player2.contains(27) || player2.contains(33) && player2.contains(15) && player2.contains(21) && player2.contains(27)
        || player2.contains(4) && player2.contains(10) && player2.contains(16) && player2.contains(22) || player2.contains(10) && player2.contains(16) && player2.contains(22) && player2.contains(28) || player2.contains(34) && player2.contains(16) && player2.contains(22) && player2.contains(28)
        || player2.contains(5) && player2.contains(11) && player2.contains(17) && player2.contains(23) || player2.contains(11) && player2.contains(17) && player2.contains(23) && player2.contains(29) || player2.contains(35) && player2.contains(17) && player2.contains(23) && player2.contains(29)
        || player2.contains(6) && player2.contains(12) && player2.contains(18) && player2.contains(24) || player2.contains(12) && player2.contains(18) && player2.contains(24) && player2.contains(30) || player2.contains(36) && player2.contains(18) && player2.contains(24) && player2.contains(30)
        || player2.contains(1) && player2.contains(8) && player2.contains(15) && player2.contains(22)  || player2.contains(8) && player2.contains(15) && player2.contains(22) && player2.contains(29) || player2.contains(36) && player2.contains(15) && player2.contains(22) && player2.contains(29)
        || player2.contains(6) && player2.contains(11) && player2.contains(16) && player2.contains(21) || player2.contains(11) && player2.contains(16) && player2.contains(21) && player2.contains(26) || player2.contains(31) && player2.contains(16) && player2.contains(21) && player2.contains(26)
        || player2.contains(2) && player2.contains(9) && player2.contains(16) && player2.contains(23) || player2.contains(30) && player2.contains(9) && player2.contains(16) && player2.contains(23)  || player2.contains(14) && player2.contains(21) && player2.contains(28) && player2.contains(35) || player2.contains(7) && player2.contains(14) && player2.contains(21) && player2.contains(28)
        || player2.contains(5) && player2.contains(10) && player2.contains(15) && player2.contains(20)|| player2.contains(10) && player2.contains(15) && player2.contains(20) && player2.contains(25) || player2.contains(12) && player2.contains(17) && player2.contains(22) && player2.contains(27) || player2.contains(17) && player2.contains(22) && player2.contains(27) && player2.contains(32)
        || player2.contains(3) && player2.contains(20) && player2.contains(27) && player2.contains(34)|| player2.contains(18) && player2.contains(23) && player2.contains(28) && player2.contains(33) || player2.contains(4) && player2.contains(9) && player2.contains(14) && player2.contains(19) || player2.contains(3) && player2.contains(10) && player2.contains(17) && player2.contains(24))
        {

        player2count+=1
        buttonDisable()
        disableReset()
        val build = AlertDialog.Builder(this)
        build.setTitle("Game Over")
        build.setMessage("Player 2 Wins \n \n " + "Do you want to play again")
        build.setPositiveButton("ok"){
                dialog,which->
            reset()

        }
        build.setNegativeButton("Exit"){
                dialog,which->
            exitProcess(1)
        }
        Handler().postDelayed(Runnable { build.show() },200)
        return 1
    }
        else if(emptyCell.contains(1) && emptyCell.contains(2) && emptyCell.contains(3) && emptyCell.contains(4) && emptyCell.contains(5) && emptyCell.contains(6)
        && emptyCell.contains(7) && emptyCell.contains(8) && emptyCell.contains(9) && emptyCell.contains(10) && emptyCell.contains(11)  && emptyCell.contains(12)
        && emptyCell.contains(13) && emptyCell.contains(14) && emptyCell.contains(15) && emptyCell.contains(16) && emptyCell.contains(17) && emptyCell.contains(18)
        && emptyCell.contains(19) && emptyCell.contains(20) && emptyCell.contains(21) && emptyCell.contains(22) && emptyCell.contains(23) && emptyCell.contains(24)
        && emptyCell.contains(25) && emptyCell.contains(26) && emptyCell.contains(27) && emptyCell.contains(28) && emptyCell.contains(29) && emptyCell.contains(30)
        && emptyCell.contains(31) && emptyCell.contains(32) && emptyCell.contains(33) && emptyCell.contains(34) && emptyCell.contains(35) && emptyCell.contains(36))
        //return 1
        {
            val build = AlertDialog.Builder(this)
            build.setTitle("Game Over")
            build.setMessage("Game Draw \n \n " + "Do you want to play again")
            build.setPositiveButton("ok"){ dialog,which->
                reset()
            }
            build.setNegativeButton("Exit"){ dialog,which->
                exitProcess(1)
            }
            build.show()
            return 1
        }
        return 0
    }



    private fun reset() {
        SwitchCounter.text="5"
        player1Value.text="X"
        player2Value.text="O"
        player1.clear()
        player2.clear()
        emptyCell.clear()
        activeUser=1;
        Player1Turn.isChecked=true
        Player2Turn.isChecked=false
        counterCount=1
        Player1Switch.isEnabled=false
        Player2Switch.isEnabled=false
        for(i in 1..36)
        {
            var buttonselected:Button?
            buttonselected=when(i)
            {
                1->BoxBtn1
                2->BoxBtn2
                3->BoxBtn3
                4->BoxBtn4
                5->BoxBtn5
                6->BoxBtn6
                7->BoxBtn7
                8->BoxBtn8
                9->BoxBtn9
                10->BoxBtn10
                11->BoxBtn11
                12->BoxBtn12
                13->BoxBtn13
                14->BoxBtn14
                15->BoxBtn15
                16->BoxBtn16
                17->BoxBtn17
                18->BoxBtn18
                19->BoxBtn19
                20->BoxBtn20
                21->BoxBtn21
                22->BoxBtn22
                23->BoxBtn23
                24->BoxBtn24
                25->BoxBtn25
                26->BoxBtn26
                27->BoxBtn27
                28->BoxBtn28
                29->BoxBtn29
                30->BoxBtn30
                31->BoxBtn31
                32->BoxBtn32
                33->BoxBtn33
                34->BoxBtn34
                35->BoxBtn35
                36->BoxBtn36
                else->{
                    BoxBtn1
                }

            }
            buttonselected.isEnabled=true
            buttonselected.text=""
            player1TV.text = "Player 1: $player1count"
            player2TV.text = "Player 2: $player2count"
        }
    }

    private fun disableReset() {
        Reset.isEnabled=false
        Handler().postDelayed(Runnable { Reset.isEnabled=true },200)

    }
    private fun buttonDisable() {
        player1.clear()
        player2.clear()
        emptyCell.clear()
        activeUser=1
        for(i in 1..36)
        {
            var buttonSelected:Button?
            buttonSelected=when(i)
            {
                1 -> BoxBtn1
                2->BoxBtn2
                3->BoxBtn3
                4->BoxBtn4
                5->BoxBtn5
                6->BoxBtn6
                7->BoxBtn7
                8->BoxBtn8
                9->BoxBtn9
                10->BoxBtn10
                11->BoxBtn11
                12->BoxBtn12
                13->BoxBtn13
                14->BoxBtn14
                15->BoxBtn15
                16->BoxBtn16
                17->BoxBtn17
                18->BoxBtn18
                19->BoxBtn19
                20->BoxBtn20
                21->BoxBtn21
                22->BoxBtn22
                23->BoxBtn23
                24->BoxBtn24
                25->BoxBtn25
                26->BoxBtn26
                27->BoxBtn27
                28->BoxBtn28
                29->BoxBtn29
                30->BoxBtn30
                31->BoxBtn31
                32->BoxBtn32
                33->BoxBtn33
                34->BoxBtn34
                35->BoxBtn35
                36->BoxBtn36
                else->
                {
                    BoxBtn1
                }
                //
            }
            buttonSelected.isEnabled = true
            buttonSelected.text=""
            player1TV.text="Player 1 : $player1count"
            player2TV.text="Player 2 : $player2count"


        }

    }
    fun newbot()
    {
        val rnd =(1..36).random()
        if(emptyCell.contains(rnd))
        {
            robot()
        }
        else{
            val buttonSelected=when(rnd){
                1->BoxBtn1
                2->BoxBtn2
                3->BoxBtn3
                4->BoxBtn4
                5->BoxBtn5
                6->BoxBtn6
                7->BoxBtn7
                8->BoxBtn8
                9->BoxBtn9
                10->BoxBtn10
                11->BoxBtn11
                12->BoxBtn12
                13->BoxBtn13
                14->BoxBtn14
                15->BoxBtn15
                16->BoxBtn16
                17->BoxBtn17
                18->BoxBtn18
                19->BoxBtn19
                20->BoxBtn20
                21->BoxBtn21
                22->BoxBtn22
                23->BoxBtn23
                24->BoxBtn24
                25->BoxBtn25
                26->BoxBtn26
                27->BoxBtn27
                28->BoxBtn28
                29->BoxBtn29
                30->BoxBtn30
                31->BoxBtn31
                32->BoxBtn32
                33->BoxBtn33
                34->BoxBtn34
                35->BoxBtn35
                36->BoxBtn36
                else->
                {
                    BoxBtn1
                }
            }



            if(player2Value.text=="X")
            {
                buttonSelected.text="X"
            }
            else
            {
                buttonSelected.text="O"
            }
            buttonSelected.setTextColor((Color.parseColor("#EC0C0C")))
            player2.add(rnd)
            buttonSelected.isEnabled=false
            var checkWinner=checkForWinner()

            if(checkWinner==1)
            {
                Handler().postDelayed({reset()},500)
            }
        }
    }
}



