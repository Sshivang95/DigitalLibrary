package com.shivang.bookhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class BookDescription : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_description)

        // url for description api http://13.235.250.119/v1/book/get_book/   token is same
    }
}