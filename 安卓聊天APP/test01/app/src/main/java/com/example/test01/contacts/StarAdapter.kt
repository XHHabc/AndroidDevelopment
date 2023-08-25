package com.example.test01.contacts


import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.test01.R
import com.example.test01.chatting.ChatActivity


class StarAdapter(val starList:List<Star>):
    RecyclerView.Adapter<StarAdapter.ViewHolder>() {
    //val prefs = getSharedPreferences("data", MODE_PRIVATE)
        inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
            var starImg: ImageView =view.findViewById(R.id.starImg)
            val starName: TextView =view.findViewById(R.id.StarName)
           // val now_text: TextView =view.findViewById(R.id.now_text)

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.listitems,parent,false)
        //注册点击事件
        val viewHolder=ViewHolder(view)
        viewHolder.itemView.setOnClickListener{
            val position=viewHolder.adapterPosition
            val star=starList[position]
            Toast.makeText(parent.context,"你的子view${star.userName}",Toast.LENGTH_SHORT).show()
        }

        viewHolder.starImg.setOnClickListener{
            val position=viewHolder.adapterPosition
            val star=starList[position]
            Toast.makeText(parent.context,"你的子image${star.imageId}",Toast.LENGTH_SHORT).show()
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val star=starList[position]
        holder.starImg.setImageResource(star.imageId)
        holder.starName.text=star.userName
        //holder.now_text.setText(prefs.getString("now_neirong","没有找到内容..."))

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, ChatActivity::class.java)
            //intent.putExtra("file_path", file.absolutePath)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount()=starList.size

}