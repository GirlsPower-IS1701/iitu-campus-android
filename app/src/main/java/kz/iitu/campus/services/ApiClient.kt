package kz.iitu.campus.services

import kz.iitu.campus.model.model.LoginResponse
import kz.iitu.campus.model.model.StudentProfile
import kz.iitu.campus.model.model.StudyPlan
import kz.iitu.campus.model.model.User
import retrofit2.http.*

interface ApiClient {
    private companion object {
        const val VI = "/v1/"
        const val ACCOUNT = VI + "accounts/"
        const val STUDENT = VI + "students/"
        const val GROUPS = VI + "groups/"
        const val REFERENCES = VI + "references/"

        const val LOGIN = ACCOUNT + "api/token/"
        const val STUDENT_PROFILE = STUDENT + "api/student_profile"

        const val STUDY_PLAN = GROUPS + "api/study_plan/"
        const val CREATE_REFERENCE = REFERENCES + "api/references/"
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
    ): StudentProfile

    @GET(STUDY_PLAN)
    suspend fun getStudyPlan(
        @Header("Authorization") bearer: String
    ): List<StudyPlan>

    @POST(CREATE_REFERENCE)
    suspend fun createReference(
        @Header("Authorization") bearer: String
    ): User
}