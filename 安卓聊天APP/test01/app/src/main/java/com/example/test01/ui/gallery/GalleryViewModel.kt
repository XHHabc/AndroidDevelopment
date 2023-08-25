package com.example.test01.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test01.Friends.Friend
import com.example.test01.R
import com.example.test01.contacts.Star


class GalleryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is gallery Fragment"
    }
    val text: LiveData<String> = _text

    //利用适配器增加数据列
    private val friendList = ArrayList<Friend>().apply{
        add(Friend(R.drawable.tou01, "好友01"))
        add(Friend(R.drawable.tou02, "好友02"))
        add(Friend(R.drawable.tou03, "好友03"))
        add(Friend(R.drawable.tou04, "好友04"))
        add(Friend(R.drawable.tou05, "好友05"))
    }
    val listFriend: ArrayList<Friend> = friendList
}