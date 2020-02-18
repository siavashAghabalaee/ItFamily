package com.zavosh.itfamily.activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.zavosh.itfamily.R

class LoginActivity : AppCompatActivity() {


    companion object{
        fun getInstanse(context: Context) : Intent{
            return Intent(context,LoginActivity::class.java)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}
