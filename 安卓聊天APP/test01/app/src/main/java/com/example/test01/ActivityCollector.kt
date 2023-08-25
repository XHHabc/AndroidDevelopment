package com.example.test01

import android.app.Activity
import java.util.*

/* 单例类ActivityCollector作为Activity的集合，对所有的Activity进行管理 */

object ActivityCollector {
    private val activities= ArrayList<Activity>()

    /* 用于向ArrayList中添加Activity */
    fun addActivity(actity:Activity){
        activities.add(actity)
    }

    /* 用于从ArrayList中移除Activity */
    fun removeActivity(actity: Activity){
        activities.remove(actity)
    }

    /* 用于将ArrayList中存储的Activity全部销毁 */
    fun finishAll(){
        for (activity in activities){
            if (!activity.isFinishing){
                activity.finish()
            }
        }
        activities.clear()
    }
}