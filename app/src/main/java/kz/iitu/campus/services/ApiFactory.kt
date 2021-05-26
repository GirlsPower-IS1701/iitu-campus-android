package kz.iitu.campus.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory{
    private const val BASE_URL = "http://3.21.244.168:8000"
    private const val BASE_URL_LOCAL= "http://10.0.2.2:8000"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getApi() = retrofit.create(ApiClient::class.java)
}