package com.example.closet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import closet.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
    //바인딩 클래스 인스턴스 생성
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        /* RecyclerView의 Divider 구분선 넣기 */
        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        binding.rvCloset.addItemDecoration(divider)

        //리사이클러뷰에 어댑터 설정
        binding.rvCloset.adapter = Adapter()

        //리사이클러뷰에 레이아웃메니저 설정
        binding.rvCloset.layoutManager = LinearLayoutManager(this)
    }//end of onCreate
}//end of MainActivity
