package com.example.test01
import android.content.Intent
import android.os.Bundle
import android.widget.*


class logonActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logon)
        //隐藏默认的标题栏
        supportActionBar?.hide()

        // 1)定义函数
        val login: Button = findViewById(R.id.login1)
        val reg: Button = findViewById(R.id.reg)
        val rem: CheckBox = findViewById(R.id.remember)
        //val back: ImageView = findViewById(R.id.error_png)

        // 2)调用数据库--存入和取出
        val prefs = getSharedPreferences("data", MODE_PRIVATE)
        val editor = prefs.edit()

        // 2.1)返回上一次勾选
        rem.isChecked = prefs.getBoolean("isRem", false)  // 返回isRem的数据,如果没有数据，则默认false

        // 3)获取数据库中已存在的用户名和密码
        val exist_user = prefs.getString("user", "")
        val exist_pass = prefs.getString("pass", "")
        // 3.1)定义文本框的函数,同样是局部变量修改后转的 全局变量
        val userEdit: TextView = findViewById(R.id.user)
        val passEdit: TextView = findViewById(R.id.pass)

        // 5）不会使用类，开机后如果有存有数据则自动写入,和下方的代码是一样的
        if (rem.isChecked) {
            // 5.2)从刚才的数据库获取
            val now_user = prefs.getString("now_user", "")
            val now_pass = prefs.getString("now_pass", "")

            // 5.4)把记住的数据写入文本框
            userEdit.setText(now_user)
            passEdit.setText(now_pass)
        }

        // 6)从BaseActivity来的--在BaseAcivity传入了是否清空文本框密码
        val pass_clean = prefs.getBoolean("cleanpass", false)// 如果没有数据则默认不是

        // 6.1)清空密码文本框
        if (pass_clean) {
            passEdit.setText("")

            // 6.2)取消勾选‘记住我’,并且传入数据库
            rem.isChecked = false
            editor.putBoolean("isRem",rem.isChecked)

            // 6.3) 把是否清空密码改成false
            editor.putBoolean("cleanpass",false)
            // 6.4)提交数据
            editor.apply() // 提交数据
        }

        login.setOnClickListener() {
            // 3.2)获取输入的用户名和密码

            val user =userEdit.text.toString();
            val pass =passEdit.text.toString()

            //4)如果账户密码数据库已存在则登录成功
            if (user == exist_user && pass == exist_pass) {

                // 5)如果勾选了记住密码
                if (rem.isChecked) {
                    // 5.1)存入输入的文本
                    editor.putString("now_user", user)
                    editor.putString("now_pass", pass)
                    editor.apply() // 提交数据

                    // 5.2)从刚才的数据库获取
                    val now_user = prefs.getString("now_user", "")
                    val now_pass = prefs.getString("now_pass", "")

                    // 5.4)把记住的数据写入文本框
                    userEdit.setText(now_user)
                    passEdit.setText(now_pass)

                    // 5.5)判断是否勾选记住密码,并保存到数据库,方便下一次在此界面包留是否勾选
                    val isRem = rem.isChecked
                    editor.putBoolean("isRem", isRem)
                    editor.apply() // 提交数据

                } else {
                    // 5.6)如果没有勾选,清空文本框
                    userEdit.setText("")
                    passEdit.setText("")
                }

                Toast.makeText(this, "登陆成功", Toast.LENGTH_LONG).show()
                // 进行跳转
                val it = Intent(this, MainActivity::class.java)
                startActivity(it)
            } else if (user != exist_user) {
                Toast.makeText(this, "登陆失败，用户名不存在", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "登陆失败，请检查密码是否正确", Toast.LENGTH_LONG).show()
            }
        }

        reg.setOnClickListener() {
            val it1 = Intent(this, register::class.java)
            startActivity(it1)
        }
        val back: TextView = findViewById(R.id.back)
        back.setOnClickListener() {
            ActivityCollector.finishAll()
        }
    }
}