package com.example.wordsolver.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsolver.Model.Alphabet

import com.example.wordsolver.R

class AlphabetAdapter(
    private val context: Context,
    private val list: List<Alphabet>
) : RecyclerView.Adapter<AlphabetAdapter.ViewHodelAlphabet>(){
    private lateinit var itemClick: ItemClick
    fun setOnClickItem(click: ItemClick){
        itemClick = click
    }
   inner class ViewHodelAlphabet(view: View,listener:ItemClick): RecyclerView.ViewHolder(view) {
        var btnChuCai : Button = view.findViewById(R.id.btnChuCai)

       init {
           btnChuCai.setOnClickListener(View.OnClickListener {
               listener.clickWord(adapterPosition)
           })
       }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlphabetAdapter.ViewHodelAlphabet {
       return ViewHodelAlphabet(LayoutInflater.from(parent.context).inflate(R.layout.item_recy_view_bang_chu_cai,parent,false),itemClick)
    }

    override fun onBindViewHolder(holder: AlphabetAdapter.ViewHodelAlphabet, position: Int) {
        val alphabet:Alphabet = list.get(position)
        holder.btnChuCai.setText(alphabet.chuCai)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    interface ItemClick {
        fun clickWord(poistion: Int)
    }
}