package com.shivang.bookhub.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.shivang.bookhub.R
import com.shivang.bookhub.adapter.DashboardRecyclerAdapter
import com.shivang.bookhub.model.Book
import com.shivang.bookhub.util.ConnectionManager
import org.json.JSONException

//// TODO: Rename parameter arguments, choose names that match
//// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [DashboadFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DashboadFragment : Fragment() {
    lateinit var recyclerDashboard: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var recyclerAdapter: DashboardRecyclerAdapter
   // lateinit var progressLayout : RelativeLayout
    //lateinit var progressBar : ProgressBar

    var bookInfoList = arrayListOf<Book>()
    /*val bookInfoList = arrayListOf<Book>(
        Book("P.S. I love You", "Cecelia Ahern", "Rs. 299", "4.5", R.drawable.ps_ily),
        Book("The Great Gatsby", "F. Scott Fitzgerald", "Rs. 399", "4.1", R.drawable.great_gatsby),
        Book("Anna Karenina", "Leo Tolstoy", "Rs. 199", "4.3", R.drawable.anna_kare),
        Book("Madame Bovary", "Gustave Flaubert", "Rs. 500", "4.0", R.drawable.madame),
        Book("War and Peace", "Leo Tolstoy", "Rs. 249", "4.8", R.drawable.war_and_peace),
        Book("Lolita", "Vladimir Nabokov", "Rs. 349", "3.9", R.drawable.lolita),
        Book("Middlemarch", "George Eliot", "Rs. 599", "4.2", R.drawable.middlemarch),
        Book("The Adventures of Huckleberry Finn", "Mark Twain", "Rs. 699", "4.5", R.drawable.adventures_finn),
        Book("Moby-Dick", "Herman Melville", "Rs. 499", "4.5", R.drawable.moby_dick),
        Book("The Lord of the Rings", "J.R.R Tolkien", "Rs. 749", "5.0", R.drawable.lord_of_rings)
    )*/

/*    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }*/
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboad, container, false)
        recyclerDashboard =view.findViewById(R.id.recyclerDashboard)
       // progressLayout= view.findViewById(R.id.progressLayout)
       // progressBar= view.findViewById(R.id.progressBar)

         // progressLayout.visibility= view.VISIBLE
    //check connectivity of internat
    if(ConnectionManager().checkconnectivity(activity as Context)){
        //internet is available
        val queue= Volley.newRequestQueue(activity as Context)
        val url= "http://13.235.250.119/v1/book/fetch_books"
        val jsonObjectRequest= object : JsonObjectRequest( Method.GET,url,null,
            Response.Listener {
                val success = it.getBoolean("success")
                try {
                    //progressLayout.visibility= view.GONE
                    if (success) {
                        val data = it.getJSONArray("data")
                        for (i in 0 until data.length()) {
                            val bookJsonObject = data.getJSONObject(i)
                            //each book data store by loop
                            val bookObject = Book(
                                bookJsonObject.getString("book_id"),
                                bookJsonObject.getString("name"),
                                bookJsonObject.getString("author"),
                                bookJsonObject.getString("rating"),
                                bookJsonObject.getString("price"),
                                bookJsonObject.getString("image")
                            )
                            bookInfoList.add(bookObject)
                            // calling layout and dashboardAdapter
                            layoutManager = LinearLayoutManager(activity)
                            recyclerAdapter =
                                DashboardRecyclerAdapter(activity as Context, bookInfoList)

                            recyclerDashboard.adapter = recyclerAdapter
                            recyclerDashboard.layoutManager = layoutManager
                            recyclerDashboard.addItemDecoration(
                                DividerItemDecoration(
                                    recyclerDashboard.context,
                                    (layoutManager as LinearLayoutManager).orientation
                                )
                            )
                        }
                    } else {
                        Toast.makeText(activity as Context, "some error Occurred!!", Toast.LENGTH_SHORT).show()
                    }
                }catch(e: JSONException){
                    Toast.makeText(activity as Context, "some unexpected error Occurred!!!", Toast.LENGTH_SHORT).show()
                }
            }, Response.ErrorListener {
                Toast.makeText(activity as Context, "volley error Occurred!", Toast.LENGTH_SHORT).show()

            }){
            override fun getHeaders(): MutableMap<String, String> {
                val headers= HashMap<String, String>()
                headers["Content-type"]= "application/json"
                headers["token"]= "38574cd7e8eafc"
                return headers
            }
        }
        queue.add(jsonObjectRequest)

        }else{
            //internet is not available
            val dialog= AlertDialog.Builder(activity as Context)
                dialog.setTitle("Error")
                dialog.setMessage("Internet Connection is not found")
                dialog.setPositiveButton("Open Setting"){ text,listner ->
                    val settingIntent= Intent(Settings.ACTION_WIRELESS_SETTINGS)
                    startActivity(settingIntent)
                    activity?.finish()
                }
                dialog.setNegativeButton("Exit"){text,listner ->
                    ActivityCompat.finishAffinity(activity as Activity)
                }
            dialog.create()
            dialog.show()
    }



        return view
    }
/*    companion object {
        *//**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DashboadFragment.
         *//*
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DashboadFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }*/
}