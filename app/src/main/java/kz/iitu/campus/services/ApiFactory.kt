package kz.iitu.campus.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory{
    private const val BASE_URL = "http://127.0.0.1:8000/v1/"

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getApi() = retrofit.create(ApiClient::class.java)
}