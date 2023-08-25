package com.example.test01.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test01.R
import com.example.test01.contacts.Star

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text


    //利用适配器增加初始数据列
    private val starList = ArrayList<Star>().apply{
        add(Star(R.drawable.tou01, "好友01"))
        add(Star(R.drawable.tou02, "好友02"))
        add(Star(R.drawable.tou03, "好友03"))
        add(Star(R.drawable.tou04, "好友04"))
        add(Star(R.drawable.tou05, "好友05"))
        add(Star(R.drawable.tou01, "好友01"))
        add(Star(R.drawable.tou02, "好友02"))
        add(Star(R.drawable.tou03, "好友03"))
        add(Star(R.drawable.tou04, "好友04"))
        add(Star(R.drawable.tou05, "好友05"))
        add(Star(R.drawable.tou01, "好友01"))
        add(Star(R.drawable.tou02, "好友02"))
        add(Star(R.drawable.tou03, "好友03"))
        add(Star(R.drawable.tou04, "好友04"))
        add(Star(R.drawable.tou05, "好友05"))
    }
    val listStar: ArrayList<Star> = starList

}