package com.example.closet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import closet.R
import closet.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
    //바인딩 클래스 인스턴스 생성
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //toolbar를 main액티비티의 actionBar로 설정
        setSupportActionBar(binding.toolbar)

        /* RecyclerView의 Divider 구분선 넣기 */
        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        binding.rvCloset.addItemDecoration(divider)

        //리사이클러뷰에 어댑터 설정
        binding.rvCloset.adapter = Adapter()

        //리사이클러뷰에 레이아웃메니저 설정
        binding.rvCloset.layoutManager = LinearLayoutManager(this)


    }//end of onCreate

    //다른 화면에서 돌아왔을때 리스트 갱신을 위한 오버라이딩
    override fun onRestart() {
        super.onRestart()
        binding.rvCloset.adapter = Adapter()

    }

    override fun onResume() {
        super.onResume()
        binding.rvCloset.adapter = Adapter()
    }


    //main액티비티 메뉴를 main_menu로 설정
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    //메뉴 메서드 처리
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.add_closet -> {
                val intent = Intent(this, AdditemActivity::class.java)
                startActivity(intent)
            }
        }
        
        return true
    }

}//end of MainActivity
