package com.example.closet

import android.R
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import closet.databinding.IntroBinding
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.MqttCallback
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttMessage
val ServerIP:String = "tcp://test.mosquitto.org:1883"  //서버 IP
var TOPIC:String = "TopicName"	//토픽
var mqttClient: MqttClient? = null
fun mqtt(savedInstanceState: Bundle?) {

    mqttClient = MqttClient(ServerIP, MqttClient.generateClientId(), null) //3번 연결설정
    mqttClient!!.connect()



    mqttClient!!.subscribe(TOPIC) //구독 설정
    mqttClient!!.setCallback(object : MqttCallback { //6번 콜백 설정
        override fun connectionLost(p0: Throwable?) {
            //연결이 끊겼을 경우
            Log.d("MQTTService","Connection Lost")
        }

        override fun messageArrived(p0: String?, p1: MqttMessage?) {
            //메세지가 도착했을 때

            //메세지 분리 처리 부분 (Serial,temp,humid,weight)

            //state_update(Serial,temp,_humid, weight)

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
/*
        var makeGson = GsonBuilder().create()
        var listType : TypeToken<MutableList<ClosetData.Closet_Data>> = object : TypeToken<MutableList<ClosetData.Closet_Data>>() {}
        var sp = getSharedPreferences("sharedData", Context.MODE_PRIVATE)
        var strContact = sp.getString("oneMessage", "")
// 변환
        ClosetList  = makeGson.fromJson(strContact,listType.type)
*/
        mqtt(null)
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