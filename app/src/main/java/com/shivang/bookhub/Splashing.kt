package com.shivang.bookhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.shivang.bookhub.activity.LogIn

class Splashing : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashing)
        Handler(Looper.getMainLooper()).postDelayed({
            val startAct = Intent(this@Splashing, LogIn::class.java)
            startActivity(startAct)
            finish()
        },2000)
    }
}