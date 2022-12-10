package com.example.closet

import android.R
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import closet.databinding.IntroBinding

//앱 초기 실행화면 (인트로) 를 위한 액티비티
class IntroActivity : Activity() {
    private val binding by lazy {
        IntroBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
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