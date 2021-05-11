package kz.iitu.campus.services

import kz.iitu.campus.model.model.LoginResponse
import kz.iitu.campus.model.model.StudentProfile
import retrofit2.http.*

interface ApiClient {
    private companion object {
        const val VI = "/v1/"
        const val ACCOUNT = VI + "accounts/"
        const val STUDENT = VI + "students/"

        const val LOGIN = ACCOUNT + "api/token/"
        const val STUDENT_PROFILE = STUDENT + "api/student_profile"
    }

    @FormUrlEncoded
    @POST(LOGIN)
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): LoginResponse

    @GET(STUDENT_PROFILE)
    suspend fun getUserInfo(
        @Header("Authorization") bearer: String
    ):StudentProfile
}