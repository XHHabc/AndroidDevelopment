package com.example.test01

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register1)

        //隐藏默认的标题栏
        supportActionBar?.hide()

        val register: Button = findViewById(R.id.register)
        register.setOnClickListener() {
            // 定义函数
            val ruser: EditText = findViewById(R.id.ruser)
            val rpassword: EditText = findViewById(R.id.rpassword)
            val rpassword02: EditText = findViewById(R.id.rpassword02)
            val user = ruser.text.toString()
            val pass = rpassword.text.toString()
            val pass02 = rpassword02.text.toString()

            // 判断男女
            val sexButton1: RadioButton = findViewById(R.id.sexButton1)
            val sexButton2: RadioButton = findViewById(R.id.sexButton2)
            val sex_nan = sexButton1.isChecked()
            val sex_nav = sexButton2.isChecked()

            if (user.length < 4 || user.length > 10) {
                // userId.setError("用户名必须为4-10个字符")
                Toast.makeText(this, "用户名必须为4-10个字符", Toast.LENGTH_SHORT).show()
                //val intent= Intent(this,Register::class.java)
                //startActivity(intent)

            } else if (user.contains(" ")) {
                //  userId.setError( "用户名不可以包含空格")
                Toast.makeText(this, "用户名不可以包含空格", Toast.LENGTH_SHORT).show()

            } else if (pass != pass02) {
                //pwd2.setError("两次密码不一致!")
                Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show()

            } else if (pass.isEmpty() || pass02.isEmpty()) {
                Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show()

            } else {
                // 调用数据库--存入和取出
                val prefs = getSharedPreferences("data", Context.MODE_PRIVATE)
                val editor = prefs.edit() //存入

                // 如果已有相同用户名--注册失败
                // 1) 获取已存用户名
                val exist_user = prefs.getString("user", "")

                // 2) 进行判断
                if (user == exist_user) {
                    Toast.makeText(this, "用户名已存在，无法注册！", Toast.LENGTH_LONG).show()
                } else {
                    // 3) 注册成功--把各种数据存入数据库
                    editor.putString("user", user)
                    editor.putString("pass", pass)
                    if (sex_nan) {
                        editor.putString("sex", "man")
                    } else if (sex_nav) {
                        editor.putString("sex", "woman")
                    } else {
                        editor.putString("sex", "hermaphrodite")
                    }
                    editor.apply() // 提交数据

                    // 3.1)注册成功--跳转
                    Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show()
                    val it = Intent(this, logonActivity::class.java)
                    startActivity(it)
                }
            }
        }
        val back: Button = findViewById(R.id.back)
        back.setOnClickListener(){
            finish()
        }
    }
}