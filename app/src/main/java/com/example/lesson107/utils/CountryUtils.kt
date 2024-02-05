package com.example.lesson107.utils

import android.content.Context
import android.net.ConnectivityManager
import com.example.lesson107.data.source.SelectedDataSource
import java.text.DecimalFormat

object CountryUtils {
    const val BASE_URL = "https://thantrieu.com/"
    const val IMAGE_URL = "resources/images/"
    var selectedDataSource = SelectedDataSource.REMOTE

    fun checkInternetState(context: Context): SelectedDataSource {
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (connMgr.activeNetwork == null) {
            SelectedDataSource.LOCAL
        } else {
            SelectedDataSource.REMOTE
        }
    }

    fun formatNumber(number: Float): String {
        val decimalFormat = DecimalFormat("#,###.#")
        return decimalFormat.format(number)
    }

    // upload to git
}