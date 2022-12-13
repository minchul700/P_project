package com.example.closet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
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


        sub_btn.setOnClickListener{
            val _closetName = binding.closetName.text.toString()
            val _Serial = binding.Serial.text.toString()
            var _img: Int? = null
            if(binding.radioButton1.isChecked){
                _img = R.drawable.img
            }
            else if(binding.radioButton2.isChecked){
                _img = R.drawable.img2
            }
            else if(binding.radioButton3.isChecked){
                _img = R.drawable.img3
            }

            add_closet(_img,_closetName,_Serial)
            finish()
        }
        back_btn.setOnClickListener{
            finish()
        }
    }
    //뒤로가기버튼 (home버튼)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home ->{
                finish()
            }
        }
        return true
    }
}