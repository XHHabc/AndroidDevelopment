package com.example.test01.Friends

data class Friend (val imageId:Int,val userName:String){
    //无参的构造方法
    constructor() : this(0, "")
}
