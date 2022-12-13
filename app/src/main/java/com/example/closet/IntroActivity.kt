package com.example.closet

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import closet.databinding.IntroBinding
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.MqttCallback
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttMessage

val ServerIP:String = "tcp://test.mosquitto.org:1883"  //서버 IP
var TOPIC_sub:String = "Closet_res"	//토픽
var TOPIC_pub:String = "Closet_req"	//토픽
var mqttClient: MqttClient? = null
var mqttState = 0

fun mqtt() {
    mqttClient = MqttClient(ServerIP, MqttClient.generateClientId(), null) //3번 연결설정
    mqttClient!!.connect()
    mqttState = 1
    mqttClient!!.subscribe(TOPIC_sub) //구독 설정
    mqttClient!!.setCallback(object : MqttCallback { //6번 콜백 설정
        override fun connectionLost(p0: Throwable?) {
            Log.d("MQTTService","Connection Lost")
            mqttState = 0
        }

        override fun messageArrived(p0: String?, p1: MqttMessage?) {
            //메세지가 도착했을 때
            val tmp: String = p1.toString()
            var arr = tmp.split("#")
            state_update(arr[0],arr[1],arr[2], arr[3])

            Log.d("MQTTService","Message Arrived : " + p1.toString()) //7번 메세지 도착
        }

        override fun deliveryComplete(p0: IMqttDeliveryToken?) {
            //메세지 전송 완료시
            Log.d("MQTTService","Delivery Complete")
        }
    })
}

//앱 초기 실행화면 (인트로) 를 위한 액티비티
class IntroActivity : Activity() {
    private val binding by lazy {
        IntroBinding.inflate(layoutInflater)
    }
    lateinit var sharedPreference : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //강제 종료 대비 서비스 시작
        startService(Intent(this, ForecdTerminationService::class.java))
        sharedPreference = getSharedPreferences("ClosetListData", 0)
        var i :Int = 0
        try {
            if(sharedPreference.getInt("ClosetNum",0) != 0) {
                set_num(sharedPreference.getInt("ClosetNum",0))
                ClosetList.clear()
                for (i in 0 until numOfCloset ) {
                    sharedPreference.getString("closetName" + i.toString(), null)?.let {
                        set_list(sharedPreference.getInt("imgRes" + i.toString(), 0),
                            it, sharedPreference.getString("Serial" + i.toString(), null)!!
                        )
                    }
                }
            }
        }catch (e : Exception){
            System.out.println(e)
        }



        mqtt()
        val handler = Handler()
        handler.postDelayed({
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent) //다음화면으로 넘어감
            finish()
        }, 3000) //3초 뒤에 다음화면
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}