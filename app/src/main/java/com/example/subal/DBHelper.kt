package com.example.subal

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//DBHelper 클래스 선언. SQLiteOpenHelper를 상속받음
class DBHelper(context: Context): SQLiteOpenHelper(context, "testdb", null, 1) {

    //데이터 베이스가 처음 생성될 때 호출됨
    override fun onCreate(p0: SQLiteDatabase?) {
        // TODO_TB라는 이름의 테이블을 생성. _id와 todo라는 두개의 필드를 가짐
        p0?.execSQL("create table TODO_TB (" +
                "_id integer primary key autoincrement," +
                "todo not null)")
    }

    //데이터 베이스가 업그레이드될 때 호출되는 메소드
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        //현재는 특별히 수행할 동작 없음....

    }
}