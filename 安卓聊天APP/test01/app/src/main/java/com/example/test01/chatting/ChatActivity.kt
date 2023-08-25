package com.example.test01.chatting


import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test01.R




class ChatActivity :AppCompatActivity(),View.OnClickListener{

    private val msgList=ArrayList<Msg>()
    private var adapter:MsgAdapter ?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatting)
        initMsg()

        //RecyclerView组件id的获取
        val recyclerView:RecyclerView =findViewById(R.id.recyclerView)
        val layoutManager=LinearLayoutManager(this)

        adapter= MsgAdapter(msgList)

        recyclerView.layoutManager =layoutManager
        recyclerView.adapter=adapter

        val send: Button =findViewById(R.id.send)
        send.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        val send: Button =findViewById(R.id.send)
        val inputText: EditText =findViewById(R.id.inputText)
        val recyclerView:RecyclerView =findViewById(R.id.recyclerView)
        when(v){
            send ->{
                val content=inputText.text.toString()
                if (content.isNotEmpty()){
                    val msg=Msg(content,Msg.TYPE_SET)
                    msgList.add(msg)
                    //当有新消息时，就刷新RecyclerView中的显示
                    adapter?.notifyItemInserted(msgList.size - 1)
                    //将显示定位到最后一行
                    recyclerView.scrollToPosition(msgList.size - 1)
                    // 建立数据库
                    val prefs = getSharedPreferences("data", MODE_PRIVATE)
                    val editor = prefs.edit()
                    val textEdit = inputText.text.toString()
                    editor.putString("now_neirong",textEdit)
                    editor.apply()
                    //清空输入框中的内容
                    inputText.setText("")
                }
            }
        }
    }



    //用方法先初始化几句应答
    private fun initMsg() {
        val msg1=Msg("你好",Msg.TYPE_RECEIVED)
        msgList.add(msg1)

        val msg2=Msg("你好.你有什么事？",Msg.TYPE_SET)
        msgList.add(msg2)

        val msg3=Msg("没事。",Msg.TYPE_RECEIVED)
        msgList.add(msg3)
    }


}
