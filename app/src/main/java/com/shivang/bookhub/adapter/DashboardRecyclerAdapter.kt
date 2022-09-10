package com.shivang.bookhub.adapter

import android.content.Intent
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.shivang.bookhub.BookDescription
import com.shivang.bookhub.R
import com.shivang.bookhub.model.Book
import com.squareup.picasso.Picasso
import android.content.Context as Context

class DashboardRecyclerAdapter(val context: Context, val itemList: ArrayList<Book>): RecyclerView.Adapter<DashboardRecyclerAdapter.DashboardViewHolder>() {

    class DashboardViewHolder(view: View): RecyclerView.ViewHolder(view){
        val txtBookName: TextView =view.findViewById(R.id.txtBookName)
        val txtAuthorName: TextView = view.findViewById(R.id.txtAuthorName)
        val txtCost: TextView = view.findViewById(R.id.txtCost)
        val txtRating: TextView = view.findViewById(R.id.txtRating)
        val imgBookImage: ImageView = view.findViewById(R.id.imgBookImage)
        val layoutBook: LinearLayout= view.findViewById(R.id.layoutBook)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
     val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_dashboard_single_row,parent,false)
    return DashboardViewHolder(view)
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
      val book = itemList[position]
        holder.txtBookName.text= book.bookName
        holder.txtAuthorName.text= book.bookAuthor
        holder.txtCost.text= book.bookprice
        holder.txtRating.text= book.bookRating
       // holder.imgBookImage.setImageResource(book.bookImage)
        Picasso.get().load(book.bookImage).error(R.drawable.default_book_cover).into(holder.imgBookImage)

        holder.layoutBook.setOnClickListener {
           val intent =Intent(context,BookDescription::class.java)
            intent.putExtra("Book Id",book.bookId)
            context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
        return itemList.size
    }

}