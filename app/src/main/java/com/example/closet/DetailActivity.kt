package com.example.closet


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import closet.R
import closet.databinding.ActivityDetailBinding
import org.eclipse.paho.client.mqttv3.MqttMessage

var act = 0
class DetailActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        
        //로딩창 종료
        customProgressDialog?.cancel()
        
        
        //toolbar를 main액티비티의 actionBar로 설정
        setSupportActionBar(binding.toolbar2)

        //toolbar에 뒤로가기 버튼 설정
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        init()

    }//end of onCreate

    fun init() {
        with(binding) {
            //인텐트에서 "CLOSET"라는 이름으로 전달된 옷장의 enum 상수명을 문자열로 추출
            var closetNo = intent.getIntExtra(CLOSET, -1)

            //추출한 상수명(CLOSET1,..n)으로 Data(enum class)에서 해당 옷장의 상수(객체)를 가져옴
            val Data = ClosetList[closetNo]

            //해당 옷장의 정보를 상세보기 레이아웃(activity_detail.xml)에 데이터 바인딩
                Data.imgRes?.let { it1 -> imgClosetPhoto.setImageResource(it1) }
                tvClosetName.text = Data.closetName
                tvClosetSerial.text = Data.Serial
                tem.text= "온도 : " + Data.temp.toString()
                hum.text= "습도 : " + Data.humid.toString()
                weight.text= "제습제 무게 : " + Data.weight.toString() + "G"

            backBtn.setOnClickListener {
                finish()
            }
            fanButton.setOnClickListener{
                mqttClient!!.publish(TOPIC_pub, MqttMessage((Data.Serial + "#FAN").toByteArray())) //4번 메세지 전송
                if(act == 0) {
                    Toast.makeText(applicationContext, "환기를 시작합니다.", Toast.LENGTH_SHORT).show()
                    act = 1
                }
                else{
                    Toast.makeText(applicationContext, "환기를 종료합니다.", Toast.LENGTH_SHORT).show()
                    act = 0
                }
            }
        }
    }

    //"CLOSET"를 전역상수로 사용하기 위해 final static으로 선언
    companion object {
        const val CLOSET = "CLOSET"
    }

    //detail toolbar를 detail_menu로 설정
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu,menu)
        return true
    }

    //메뉴 메서드 처리
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home ->{
                finish()
            }

            R.id.delete_closet -> {
                delete_closet(intent.getIntExtra(CLOSET, -1))
                finish()
            }
        }

        return true
    }
}//end of DetailActivity