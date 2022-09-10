package com.shivang.bookhub.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.shivang.bookhub.R

class ForgetPassword : AppCompatActivity() {

    lateinit var btnNext:Button
    lateinit var etRegisteredPhone:EditText
    lateinit var etRegisteredEmail:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        btnNext=findViewById(R.id.btnNext)
        etRegisteredPhone=findViewById(R.id.etRegisteredPhone)
        etRegisteredEmail=findViewById(R.id.etRegisteredEmail)

        btnNext.setOnClickListener {
            Toast.makeText(this@ForgetPassword,"New password is sent to your Email",Toast.LENGTH_LONG).show()
            val intent= Intent(this, LogIn::class.java)
            startActivity(intent)
            finish()
        }
    }
}