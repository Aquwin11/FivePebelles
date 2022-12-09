package com.example.fivepebells


import android.annotation.SuppressLint
import android.app.TaskInfo
import android.content.ContentValues
import android.content.Context
import android.content.LocusId
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.widget.TextView
import com.example.sqlliteexample.UserModel
import java.util.ArrayList
import java.util.prefs.PreferencesFactory


public class SQLiteManager(context: Context):
SQLiteOpenHelper(context,DB_NAME,null,DB_Version)
{
    companion object{
        private val DB_NAME = "UserNameDetails.db"
        private val DB_Version = 1
        /*private val TABLE_NAME = "UserSettings"
        private val ID = "UserID"
        private val UserNAME = "UserName"
        private val LightMode = "LightSwitch"
        private val MuteMode = "MuteAudio"
        private var VolumeValue = "VolumeButton"*/
        private val SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UserSettingValue.UserEntry.TABLE_NAME + " (" +
                    UserSettingValue.UserEntry.ID + " TEXT PRIMARY KEY," +
                    UserSettingValue.UserEntry.UserName + " TEXT," +
                    UserSettingValue.UserEntry.VolumeProgress + " TEXT)"
                    //UserSettingValue.UserEntry.MuteMode + "TEXT"

        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + UserSettingValue.UserEntry.TABLE_NAME
    }

    override fun onCreate(p0: SQLiteDatabase) {
        p0.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    @Throws(SQLiteConstraintException::class)
    fun insertUser(user:UserModel): Boolean {
        // Gets the data repository in write mode
        val db = writableDatabase

        // Create a new map of values, where column names are the keys
        val values = ContentValues()
        values.put(UserSettingValue.UserEntry.ID, user.userid)
        values.put(UserSettingValue.UserEntry.UserName, user.username)
        values.put(UserSettingValue.UserEntry.VolumeProgress, user.volumeProgress)
/*        values.put(UserSettingValue.UserEntry.MuteMode, user.MuteButton)
        values.put(UserSettingValue.UserEntry.LightMode, user.LightButton)*/


        // Insert the new row, returning the primary key value of the new row
        db.insert(UserSettingValue.UserEntry.TABLE_NAME, null, values)

        return true
    }
    @Throws(SQLiteConstraintException::class)
    fun deleteUser(userid: String): Boolean {
        // Gets the data repository in write mode
        val db = writableDatabase
        // Define 'where' part of query.
        val selection = UserSettingValue.UserEntry.ID + " LIKE ?"
        // Specify arguments in placeholder order.
        val selectionArgs = arrayOf(userid)
        // Issue SQL statement.
        db.delete(UserSettingValue.UserEntry.TABLE_NAME, selection, selectionArgs)

        return true
    }


    /*fun addTask(tasks : UserSettingValue): Boolean{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(UserNAME,tasks.UserName)
        val _success = db.insert(TABLE_NAME,null,values)
        db.close()
        return (Integer.parseInt("$_success")!=-1)
    }

    fun getTask(_id:Int):UserSettingValue{
        val task = UserSettingValue()
        val db = this.writableDatabase
        val selectQuery = "SELECT *FROM $TABLE_NAME WHERE $ID=$_id"
        val cursor = db.rawQuery(selectQuery,null)

        cursor?.moveToFirst()
        task.id=Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID)))
        task.USERNAME = cursor.getString(cursor.getColumnIndex(TABLE_NAME))
        task.VOLUMEPROGRESS = cursor.getInt(cursor.getColumnIndex(VolumeValue))
        cursor.close()
        return task
    }

    fun showAllUsers(){
        val users = usersDBHelper.readAllUsers()
        binding.llEntries.removeAllViews()
        users.forEach {
            val tvUser = TextView(this)
            tvUser.textSize = 30F
            tvUser.text = "${it.name.toString()} - ${it.age.toString()}"
            binding.llEntries.addView(tvUser)
        }
        binding.textviewResult.text = "Fetched ${users.size} users"
    }
*/
    /*fun updateTask(tasks:UserSettingValue.UserEntry):Boolean
    {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(UserNAME,tasks.USERNAME)
        values.put(VolumeValue,tasks.VOLUMEPROGRESS)
        val _success = db.update(TABLE_NAME,values, ID+ "=?", arrayOf(tasks.id.toString())).toLong()
        db.close()
        return Integer.parseInt("$_success")!=-1
    }*/


    fun readAllUsers(): ArrayList<UserModel> {
        val users = ArrayList<UserModel>()
        val db = writableDatabase
        val cursor: Cursor?
        try {
            cursor = db.rawQuery("select * from " + UserSettingValue.UserEntry.TABLE_NAME, null)
        } catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }

        var userid = ""
        var username = ""
        var volume = ""
        var Mute = ""
        var Mode = ""
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {

                val userIdTemp = cursor.getColumnIndex(UserSettingValue.UserEntry.ID)
                if (userIdTemp >= 0) {
                    userid = cursor.getString(userIdTemp)
                }
                val nameTemp = cursor.getColumnIndex(UserSettingValue.UserEntry.UserName)
                if (nameTemp >= 0) {
                    username = cursor.getString(nameTemp)
                }
                val valueTemp = cursor.getColumnIndex(UserSettingValue.UserEntry.VolumeProgress)
                if (valueTemp>= 0) {
                    volume = cursor.getString(valueTemp)
                }
                /*val muteTemp = cursor.getColumnIndex(UserSettingValue.UserEntry.MuteMode)
                if (muteTemp >= 0) {
                    Mute = cursor.getString(muteTemp)
                }*/
                /*val modeTemp = cursor.getColumnIndex(UserSettingValue.UserEntry.LightMode)
                if (modeTemp >= 0) {
                    Mode = cursor.getString(modeTemp)
                }*/
                users.add(UserModel(userid,username,volume.toInt()/*,Mute*/))
                cursor.moveToNext()
            }
        }
        cursor.close()
        return users
    }

}