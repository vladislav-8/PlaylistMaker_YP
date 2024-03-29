package com.practicum.playlistmaker_1.search.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RetrofitNetworkClient(
    private val api: TracksApi,
    private val context: Context
) : NetworkClient {

    override suspend fun doRequest(dto: Any): Response {
        if (!isConnected()) {
            return Response().apply { resultCode = STATUS_CODE_NO_NETWORK_CONNECTION }
        }

        if (dto !is TrackSearchRequest) {
            return Response().apply { resultCode = STATUS_CODE_BAD_REQUEST }
        }

        return withContext(Dispatchers.IO) {
            try {
                val response = api.searchTrack(dto.expression)
                response.apply { resultCode = STATUS_CODE_SUCCESS }
            } catch (e: Throwable) {
                Response().apply { resultCode = STATUS_CODE_SERVER_ERROR }
            }
        }
    }

    private fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            }
        }
        return false
    }

    companion object {
        const val STATUS_CODE_SUCCESS = 200
        const val STATUS_CODE_SERVER_ERROR = 500
        const val STATUS_CODE_BAD_REQUEST = 400
        const val STATUS_CODE_NO_NETWORK_CONNECTION = -1
    }
}