package com.example.closet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.get
import closet.R
import closet.databinding.AddItemBinding

class AdditemActivity : AppCompatActivity() {

    private val binding by lazy {
        AddItemBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //toolbar를 main액티비티의 actionBar로 설정
        setSupportActionBar(binding.toolbar3)

        //toolbar에 뒤로가기 버튼 설정
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val sub_btn = binding.addClosetBtn
        val back_btn = binding.exit
        val _closetName = binding.textInputLayout.editText?.text.toString()
        val _Serial = binding.textInputLayout2.editText?.text.toString()

        sub_btn.setOnClickListener{
            add_closet(R.drawable.img,_closetName,_Serial)
            finish()
        }
        back_btn.setOnClickListener{
            finish()
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home ->{
                //home 버튼 메서드
                finish()
            }

            R.id.add_closet_btn -> {
                /*
                val tmp = intent.getStringExtra(CLOSET)
                val mIntent = Intent(tmp, DetailActivity::class.java)

                Log.i("CLOSET", "Data:" + ClosetData.closetName)

                /* 클릭한 itemView에 맞는 Data의 상수명을 인텐트에 저장
                  - Data.name : enum class에 선언된 상수이름을 반환  */
                mIntent.putExtra(CLOSET, ClosetData.name)
                itemView.context.startActivity(mIntent)*/
            }
        }
        return true
    }
}