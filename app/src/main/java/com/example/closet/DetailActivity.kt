package com.example.closet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import closet.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()

    }//end of onCreate

    fun init() {
        with(binding) {
            //인텐트에서 "CLOSET"라는 이름으로 전달된 옷장의 enum 상수명을 문자열로 추출
            val closetName = intent.getStringExtra(CLOSET)

            //추출한 상수명(CLOSET1,..n)으로 Data(enum class)에서 해당 옷장의 상수(객체)를 가져옴
            val Data =closetName?.let {
                ClosetData.valueOf(it) }//it과 일치하는 enum 상수(객체)를 가져옴

            //해당 옷장의 정보를 상세보기 레이아웃(activity_detail.xml)에 데이터 바인딩
            Data?.let {
                imgClosetPhoto.setImageResource(Data.imgRes)
                tvClosetName.text = Data.closetName
                tvClosetSerial.text = Data.Serial
                tem.text= Data.temp.toString()
                hum.text= Data.humid.toString()
                weight.text= Data.weight.toString()
            }

            backBtn.setOnClickListener {
                finish()
            }
        }
    }

    //"CLOSET"를 전역상수로 사용하기 위해 final static으로 선언
    companion object {
        const val CLOSET = "CLOSET"
    }
}//end of DetailActivity