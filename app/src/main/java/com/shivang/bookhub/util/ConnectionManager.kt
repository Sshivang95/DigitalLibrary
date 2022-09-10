package com.shivang.bookhub.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.core.content.getSystemService

class ConnectionManager {
    fun checkconnectivity(context: Context): Boolean{

        // to fatch the info of all network available in device from connectivity manager $for better understanding use same name for variable
        val connectivityManager= context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        //is network is connected or not
        val activeNetwork: NetworkInfo?= connectivityManager.activeNetworkInfo

        if (activeNetwork?.isConnected!= null){
            return activeNetwork.isConnected
        }else{
            return false
        }
    }
}