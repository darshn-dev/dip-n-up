package dev.darshn.dipnup.data.remote

import okhttp3.ResponseBody
import retrofit2.http.GET

interface StockApi {

    @GET("query?function=LISTING_STATUS")
    suspend fun getCompanyList(
    ): ResponseBody


    companion object {
        const val API_KEY = "FJDJOSAU7PDP9OAO"
        const val BASE_URL = "https://www.alphavantage.co"
    }
}