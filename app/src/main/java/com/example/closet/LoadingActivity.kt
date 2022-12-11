package com.example.closet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import closet.R
import closet.databinding.ActivityMainBinding
import closet.databinding.DialogProgressBinding

class LoadingActivity : AppCompatActivity() {

    private val binding by lazy {
        DialogProgressBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}