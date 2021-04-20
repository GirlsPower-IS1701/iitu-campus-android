package kz.iitu.campus.services

import kz.iitu.campus.pojo.auth.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiClient {
    private companion object {
        const val ACCOUNT = "accounts/"
        const val LOGIN = ACCOUNT + "api/token/"
    }

    @POST(LOGIN)
    @FormUrlEncoded
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): LoginResponse
}