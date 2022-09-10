package com.shivang.bookhub.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.shivang.bookhub.*
import com.shivang.bookhub.fragment.AboutFragment
import com.shivang.bookhub.fragment.DashboadFragment
import com.shivang.bookhub.fragment.FavouriteFragment
import com.shivang.bookhub.fragment.ProfileFragment


class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout:DrawerLayout
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var frameLayout: FrameLayout
    lateinit var toolbar:androidx.appcompat.widget.Toolbar
    lateinit var navigationView: NavigationView
     var previousMenuItem: MenuItem? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //initialize the variables
        drawerLayout=findViewById(R.id.drawerlayout)
        coordinatorLayout=findViewById(R.id.coordinatorlayout)
        frameLayout=findViewById(R.id.frame)
        toolbar=findViewById(R.id.toolbar)
        navigationView=findViewById(R.id.navigation)
        // calling of tool bar and dashboard
        setUpToolbar()
        openDashboard()
        //set home button icon as hamburgerToggle or icon
        val actionBarDrawerToggle=ActionBarDrawerToggle(
            this@MainActivity,
                     drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer
        )
        //make hamburger icon functional
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        //adding click listener to the menu item
        navigationView.setNavigationItemSelectedListener{
            // for checkable/marked behaviour of menu items
            if (previousMenuItem != null)
                previousMenuItem?.isChecked= false

            it.isCheckable= true
            it.isChecked=true
            previousMenuItem = it
            // for selection of fragments
            when(it.itemId){
                R.id.dashboard -> {
                    openDashboard()
                    drawerLayout.close()
                }
                R.id.favourite -> {

                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, FavouriteFragment())
                        //.addToBackStack("Favourite")
                        .commit()

                    supportActionBar?.title="Favourite"
                    drawerLayout.close()
                }
                R.id.profile -> {

                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, ProfileFragment())
                        //.addToBackStack("Profile")
                        .commit()
                    supportActionBar?.title="Profile"
                    drawerLayout.close()
                }
                R.id.about -> {

                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, AboutFragment())
                        //.addToBackStack("About")
                        .commit()

                    supportActionBar?.title="About"
                    drawerLayout.close()
                }
            }
            return@setNavigationItemSelectedListener true
        }
       /* navigationView.setNavigationItemSelectedListener{

            when (it.itemId){
                R.id.dashboard ->{
                    Toast.makeText(this@MainActivity,
                        "Clicked on dashboard",
                        Toast.LENGTH_SHORT).show()
                }
                R.id.favourite ->{
                    Toast.makeText(this@MainActivity,
                        "Clicked on favourites",
                        Toast.LENGTH_SHORT).show()
                }
                R.id.profile ->{
                    Toast.makeText(this@MainActivity,
                        "Clicked on profile",
                        Toast.LENGTH_SHORT).show()
                }
                R.id.about ->{
                    Toast.makeText(this@MainActivity,
                        "Clicked on about",
                        Toast.LENGTH_SHORT).show()
                }
            }

            return@setNavigationItemSelectedListener true

        }*/



    }
    //Open drawerlayout from start of screen by Toggle button
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
          val id= item.itemId
        if (id == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }

    //set tool bar as action bar
    fun setUpToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title="Home"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
    fun openDashboard(){
        val fragment= DashboadFragment()
        val transection= supportFragmentManager.beginTransaction()

        transection.replace(R.id.frame,fragment)
        transection.commit()

        supportActionBar?.title="Dashboard"
        navigationView.setCheckedItem(R.id.dashboard)
    }
    // back always to dashboard
    override fun onBackPressed() {
        val frag = supportFragmentManager.findFragmentById(R.id.frame)
        when(frag) {
            !is DashboadFragment -> openDashboard()

            else -> super.onBackPressed()
        }
    }

}