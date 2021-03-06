package com.apm.trackify.ui.main

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import com.apm.trackify.R

class NetworkConnection(private val connectivityManager: ConnectivityManager) :
    LiveData<Boolean>() {

    constructor(application: Application) : this(
        application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    )

    private val networkCallback = @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            postValue(true)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            postValue(false)
        }
    }

    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onActive() {
        super.onActive()
        val builder = NetworkRequest.Builder()
        connectivityManager.registerNetworkCallback(builder.build(), networkCallback)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onInactive() {
        super.onInactive()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun manualCheck(context: Context) {
       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
           val network = connectivityManager.activeNetwork
           if (network != null) {
               val actNetwork = connectivityManager.getNetworkCapabilities(network)
               if (actNetwork != null) {
                   when {
                       actNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                           postValue(true)
                       }
                       actNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                           postValue(true)
                       }
                       actNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                           postValue(true)
                       }
                       else -> {
                           postValue(false)
                           Toast.makeText(context, R.string.alert_bad_retry, Toast.LENGTH_SHORT).show()
                       }
                   }
               } else {
                   postValue(false)
                   Toast.makeText(context, R.string.alert_bad_retry, Toast.LENGTH_SHORT).show()
               }
           } else {
               postValue(false)
               Toast.makeText(context, R.string.alert_bad_retry, Toast.LENGTH_SHORT).show()
           }
       }
    }
}