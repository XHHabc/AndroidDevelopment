package com.example.test01.Friends


import android.app.AlertDialog
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


class FriendAdapter(val friendList:ArrayList<Friend>):
    RecyclerView.Adapter<FriendAdapter.ViewHolder>() {
    //val prefs = getSharedPreferences("data", MODE_PRIVATE)
        inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
            var starImg: ImageView =view.findViewById(R.id.starImg)
            val starName: TextView =view.findViewById(R.id.StarName)
        val deleteImageView: ImageView =view.findViewById(R.id.deleteImageView)
           // val now_text: TextView =view.findViewById(R.id.now_text)

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.listitems_friend,parent,false)
        //注册点击事件
        val viewHolder=ViewHolder(view)
        viewHolder.itemView.setOnClickListener{
            val position=viewHolder.adapterPosition
            val friend=friendList[position]
            Toast.makeText(parent.context,"你的子view${friend.userName}",Toast.LENGTH_SHORT).show()
        }

        viewHolder.starImg.setOnClickListener{
            val position=viewHolder.adapterPosition
            val friend=friendList[position]
            Toast.makeText(parent.context,"你的子image${friend.imageId}",Toast.LENGTH_SHORT).show()
        }
        viewHolder.deleteImageView.setOnClickListener{
            val position=viewHolder.adapterPosition
            val friend=friendList[position]

            //设置弹窗提示，确定后再删除刷新
            val dialogBuilder = AlertDialog.Builder(view.context)

            dialogBuilder.setTitle("确认删除")
            dialogBuilder.setMessage("您确定要删除好友 ${friend.userName} 吗？")

            dialogBuilder.setPositiveButton("确认") { dialog, which ->
                friendList.remove(friend)
                //再删除操作触发后进行显示刷新
                notifyDataSetChanged()
            }
            dialogBuilder.setNegativeButton("取消") { dialog, which ->
                dialog.dismiss()
            }
            //创建一个对话框并将其显示在屏幕上
            val dialog = dialogBuilder.create()
            dialog.show()
            //friendList.remove(friend)
            //再删除操作触发后进行显示刷新
            //notifyDataSetChanged()
           // Toast.makeText(parent.context,"你的删除按钮",Toast.LENGTH_SHORT).show()
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val friend=friendList[position]
        holder.starImg.setImageResource(friend.imageId)
        holder.starName.text=friend.userName
        //holder.now_text.setText(prefs.getString("now_neirong","没有找到内容..."))

        //holder.itemView.setOnClickListener {
/*            val intent = Intent(holder.itemView.context, ChatActivity::class.java)
            //intent.putExtra("file_path", file.absolutePath)
            holder.itemView.context.startActivity(intent)*/
        //}
    }

    override fun getItemCount()=friendList.size


}