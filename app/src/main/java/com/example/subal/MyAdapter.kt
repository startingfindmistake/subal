package com.example.subal

import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.subal.databinding.ItemRecyclerviewBinding

//ViewHolder클래스를 정의한다. 이 클래스는 RecyclerView의 각 항목을 표시하는 데 사용된다.
class MyViewHolder(val binding: ItemRecyclerviewBinding): RecyclerView.ViewHolder(binding.root)

//Adapter 클래스를 정의한다. 이 클래스는 RecyclerView의 데이터 관리하고 각 항목 뷰를 생성하는 데 사용된다.
class MyAdapter(val datas: MutableList<String>?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    //getItemCount 메서드는 RecyclerView의 항목 수를 반환한다.
    override fun getItemCount(): Int{
        return datas?.size ?: 0
    }
    //onCreateViewHolder메서드는 viewHolder를 생성하고 초기화한다.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = MyViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    //onBindViewHolder 메서드는 viewHolder를 데이터와 연결 bind한다
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding=(holder as MyViewHolder).binding
        //데이터 리스트에서 해당 위치의 데이터를 가져와 viewHolder에 설정한다.
        binding.itemData.text= datas!![position]
    }
}
