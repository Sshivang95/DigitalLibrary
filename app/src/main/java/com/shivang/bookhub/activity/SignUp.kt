package com.shivang.bookhub.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.shivang.bookhub.R

class SignUp : AppCompatActivity() {

    lateinit var btnRegister:Button
    lateinit var etName:EditText
    lateinit var etPhone:EditText
    lateinit var etEmail:EditText
    lateinit var etAddress:EditText
    lateinit var etSetPass:EditText
    lateinit var etConfirmPass:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        btnRegister=findViewById(R.id.btnRegister)
        etName=findViewById(R.id.etName)
        etPhone=findViewById(R.id.etPhone)
        etEmail=findViewById(R.id.etEmail)
        etAddress=findViewById(R.id.etAddress)
        etSetPass=findViewById(R.id.etSetPass)
        etConfirmPass=findViewById(R.id.etConfirmPass)

        btnRegister.setOnClickListener {
            Toast.makeText(this@SignUp,"you have registered successfully",Toast.LENGTH_LONG).show()
            val intent= Intent(this, LogIn::class.java)
            startActivity(intent)
            finish()
        }
    }
}