package com.example.test01.chatting

class Msg(val content:String,val type:Int) {
    companion object{
        //设置接收和发送的常量
        const val TYPE_RECEIVED=0
        const val TYPE_SET=1

    }
}