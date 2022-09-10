package com.shivang.bookhub.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.shivang.bookhub.R

class LogIn : AppCompatActivity() {

    //creating variable of all responsive views of there respective view class
    lateinit var etUserid:EditText
    lateinit var etPassword :EditText
    lateinit var btnLogin:Button
    lateinit var txtForgetPassword:TextView
    lateinit var txtSignup:TextView
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences=getSharedPreferences(getString(R.string.preference_file_name),
            MODE_PRIVATE)
        val IsLoginId=sharedPreferences.getBoolean("IsLoginId",false)
        setContentView(R.layout.log_in)

        if (IsLoginId){
            val intent=Intent(this@LogIn, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


        //initialize the variable by it's view id
        etUserid= findViewById(R.id.etUserid)
        etPassword=findViewById(R.id.etPassword)
        btnLogin= findViewById(R.id.btnLogin)
        txtForgetPassword=findViewById(R.id.txtForgetPassword)
        txtSignup=findViewById(R.id.txtSignup)

        //activate login button
        btnLogin.setOnClickListener {
            //store userid password
            val loginId = etUserid.text.toString()
            val password= etPassword.text.toString()
            savePreference()
            val intent=Intent(this@LogIn, MainActivity::class.java)
           startActivity(intent) //Toast.makeText(this@LogIn, "button is activated", Toast.LENGTH_LONG).show()
        }
        //activate forgetpassword text
        txtForgetPassword.setOnClickListener {
            val intent=Intent(this@LogIn, ForgetPassword::class.java)
            startActivity(intent)
            //Toast.makeText(this@LogIn, "this is not working", Toast.LENGTH_LONG).show()
        }
        //activate signup text
        txtSignup.setOnClickListener {
            val intent=Intent(this@LogIn, SignUp::class.java)
            startActivity(intent)
            //Toast.makeText(this@LogIn, "this is not working", Toast.LENGTH_LONG).show()
        }
    }
    fun savePreference(){
        sharedPreferences.edit().putBoolean("IsLoginId",true).apply()
    }
    override fun onPause() {
        super.onPause()
        finish()
    }
}