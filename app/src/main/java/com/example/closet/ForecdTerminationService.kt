package com.example.closet

import android.app.Service
import android.content.Intent
import android.content.SharedPreferences
import android.os.IBinder
import androidx.annotation.Nullable


class ForecdTerminationService : Service() {
    @Nullable
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
    lateinit var sharedPreference : SharedPreferences
    override fun onTaskRemoved(rootIntent: Intent) { //핸들링 하는 부분
        sharedPreference = getSharedPreferences("ClosetListData", 0)
        val editor = sharedPreference.edit()
        editor.clear()
        editor.apply()
        editor.putInt("ClosetNum", numOfCloset)
        for(i in 0 until numOfCloset ) {
            var sharedImgRes: Int? = ClosetList[i].imgRes
            var sharedClosetName: String?= ClosetList[i].closetName
            var sharedSerial: String?= ClosetList[i].Serial
            if (sharedImgRes != null) {
                editor.putInt("imgRes" + i.toString(), sharedImgRes)
            }
            editor.putString("closetName" + i.toString(), sharedClosetName)
            editor.putString("Serial" + i.toString(), sharedSerial)
        }
        editor.commit()
        stopSelf() //서비스 종료
    }

}