package com.example.closet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import closet.R
import closet.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        //toolbar를 main액티비티의 actionBar로 설정
        setSupportActionBar(binding.toolbar2)

        //toolbar에 뒤로가기 버튼 설정
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        
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

    //detail toolbar를 detail_menu로 설정
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu,menu)
        return true
    }

    //메뉴 메서드 처리
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home ->{
                //home 버튼 메서드
                finish()
            }
            
            R.id.delete_closet -> {
                // 옷장 제거 메서드 추가
            }
        }

        return true
    }
}//end of DetailActivity