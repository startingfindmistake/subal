package com.example.subal

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.subal.databinding.ActivityAddBinding

//AddActivity라는 이름의 액티비티 클래스를 정의한다
class AddActivity : AppCompatActivity() {
    //늦은 초기화를 위한 binding 변수를 선언
    //이 변수는 ActivityAddBinding 클래스의 인스턴스를 참조한다.
    lateinit var binding: ActivityAddBinding

    //액티비티가 생성될 때 호출되는 메서드이다
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //레이아웃 인플레이터를 사용하여 binding변수를 초기화 한다.
        binding= ActivityAddBinding.inflate(layoutInflater)
        //액티비티의 콘텐츠 뷰를 설정한다.
        setContentView(binding.root)
    }

    //옵션 메뉴를 생성하는 메서드이다
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //메뉴 인플레이터를 사용하여 메뉴를 생성
        menuInflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //옵션 아이템이 선택되었을 때 호출되는 메서드
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId){
        R.id.menu_add_save ->{
            //사용자가 '저장'메뉴 아이템을 선택하면, 이부분의 코드가 실행된다
            //사용자가 입력한 데이터를 가져옵니다
            //add........................
            val inputData = binding.addEditView.text.toString()
            //데이터베이스에 쓰기 위한 객체를 가져온다
            val db = DBHelper(this).writableDatabase
            //데이터베이스에 쿼리를 실행하여 데이터를 추가한다.
            db.execSQL(
                "insert into TODO_TB (todo) values (?)",
                arrayOf<String>(inputData)
            )
            //데이터베이스 닫는다
            db.close()
            //결과를 인텐트에 추가한다
            val intent = intent
            intent.putExtra("result", inputData)
            //결과를 설정하고 액티비티를 종류한다.
            setResult(Activity.RESULT_OK, intent)
            finish()
            true
        }
        else -> true
    }
}