package com.example.test01.chatting

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test01.R
import android.view.LayoutInflater

class MsgAdapter(val msgList:List<Msg>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class LeftViewHolder(view:View):RecyclerView.ViewHolder(view){
        val leftMsg:TextView=view.findViewById(R.id.leftMsa)
    }
    inner class RightViewHolder(view:View):RecyclerView.ViewHolder(view){
        val rightMsg:TextView=view.findViewById(R.id.rightMsg)
    }
    override fun getItemViewType(position: Int): Int {
        val msg=msgList[position]
        return msg.type
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=if(viewType==Msg.TYPE_RECEIVED){
        val view=LayoutInflater.from(parent.context).inflate(R.layout.msa_left_item,parent,false)
        LeftViewHolder(view)
    }else{
        val view=LayoutInflater.from(parent.context).inflate(R.layout.msa_right_item,parent,false)
        RightViewHolder(view)
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val msg=msgList[position]
        when(holder){
            is LeftViewHolder -> holder.leftMsg.text=msg.content
            is RightViewHolder -> holder.rightMsg.text=msg.content
            else -> throw IllegalAccessException()
        }
    }

    override fun getItemCount()=msgList.size
}