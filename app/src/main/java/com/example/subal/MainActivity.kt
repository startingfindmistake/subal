package com.example.subal

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.subal.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    //필요한 변수들을 선언한다. lateinit키워드는 이 변수들이 나중에 초기화될 것을 나타냄
    lateinit var binding: ActivityMainBinding
    var datas: MutableList<String>? = null
    lateinit var adapter: MyAdapter

    //액티비티가 생성될 때 호출된다. 여기서는 레이아웃 설정, 데이터로드, 리사이클러뷰 설정
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        //결과를 받아오는 런처를 등록
        val requestLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            it.data!!.getStringExtra("result")?.let {
                datas?.add(it)
                adapter.notifyDataSetChanged()
            }
        }

        //FAB를 클릭하면 AddActivity로 이동
        binding.mainFab.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            requestLauncher.launch(intent)
        }

        datas= mutableListOf<String>()

        //add......................
        //데이터베이스에서 데이터를 가져옴
        val db = DBHelper(this).readableDatabase
        val cursor = db.rawQuery("select * from TODO_TB", null)
        cursor.run {
            while(moveToNext()){
                datas?.add(cursor.getString(1))
            }
        }
        db.close()

        //리사이클러뷰 설정
        val layoutManager = LinearLayoutManager(this)
        binding.mainRecyclerView.layoutManager=layoutManager
        adapter=MyAdapter(datas)
        binding.mainRecyclerView.adapter=adapter
        binding.mainRecyclerView.addItemDecoration(
            DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        )
    }

    //옵션 메뉴를 생성할 때 호출, 메뉴를 인플레이트 한다.
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //옵션 메뉴의 항목 선택 호출, 선택된 항목에 따라 적절한 동작 수행
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_main_setting -> {
                val intent = Intent(this, setting::class.java)
                startActivity(intent)
                true
            }
            R.id.menu_main_delete -> {

                //HBHelper 인스턴스 생성
                val dbHelper = DBHelper(this)
                val koko = 1

                //데이터 삭제
                dbHelper.deleteData(koko)

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}