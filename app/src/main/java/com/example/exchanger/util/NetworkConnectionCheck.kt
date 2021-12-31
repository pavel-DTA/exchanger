package com.example.exchanger.util

import android.content.Context
import android.net.ConnectivityManager

class NetworkConnectionCheck {
    companion object {
        fun isConnect(ctx: Context): Boolean {
            val connectivityManager = ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            if (connectivityManager !== null) {
                val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                if (capabilities !== null) return true
            }
            return false
        }
    }
}