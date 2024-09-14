package com.example.sportik.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.example.sportik.data.Resource
import com.example.sportik.data.dto.ListNewsDto
import com.example.sportik.data.dto.NewsWithContentDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class RetrofitNetworkClient(
    private val context: Context,
    private val sportsApi: SportsApi
) : NetworkClient {

    override suspend fun getNews(page: Int): Resource<ListNewsDto> {
        var news: Resource<ListNewsDto>
        if (!isConnected()) return Resource.ConnectionError(DEVICE_IS_OFFLINE)
        withContext(Dispatchers.IO) {
            news = try {
                val from = (page - 1) * 10
                sportsApi.getNewsPaginated(from, 10).body()?.let {
                    Resource.Data(it)
                } ?: Resource.NotFound(NOT_FOUND)
            } catch (ex: IOException) {
                Log.e(REQUEST_ERROR, ex.toString())
                Resource.ConnectionError(REQUEST_ERROR)
            }
        }
        return news
    }

    override suspend fun getNewsWithDetails(id: Int): Resource<NewsWithContentDto> {
        var news: Resource<NewsWithContentDto>
        if (!isConnected()) return Resource.ConnectionError(DEVICE_IS_OFFLINE)
        withContext(Dispatchers.IO) {
            news = try {
                sportsApi.getNewsWithDetails(id).body()?.let {
                    Resource.Data(it)
                } ?: Resource.NotFound(NOT_FOUND)
            } catch (ex: IOException) {
                Log.e(REQUEST_ERROR, ex.toString())
                Resource.ConnectionError(REQUEST_ERROR)
            }
        }
        return news
    }

    private fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || capabilities.hasTransport(
                    NetworkCapabilities.TRANSPORT_WIFI
                ) || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            }
        }
        return false
    }

    companion object {
        private const val REQUEST_ERROR = "error_400"
        private const val NOT_FOUND = "error_404"
        private const val DEVICE_IS_OFFLINE = "you_are_offline"
    }

}