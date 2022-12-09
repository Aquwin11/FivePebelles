package com.example.fivepebells

import android.provider.BaseColumns

object UserSettingValue {
    class UserEntry : BaseColumns {
        companion object {
            const val TABLE_NAME = "users"
            const val ID = "userid"
            const val UserName = "username"
            const val VolumeProgress = "VolumeValue"
            //const val  MuteMode = "MuteAudio"
            //const val LightMode = "LightMode"
        }
    }

}
