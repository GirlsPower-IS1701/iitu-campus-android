package kz.iitu.campus.services

import kz.iitu.campus.model.academic_calendar.AcademicCalendarDto
import kz.iitu.campus.model.model.*
import retrofit2.http.*

interface ApiClient {
    private companion object {
        const val VI = "/v1/"
        const val ACCOUNT = VI + "accounts/"
        const val STUDENT = VI + "students/"
        const val GROUPS = VI + "groups/"
        const val REFERENCES = VI + "references/"
        const val CALENDAR = VI + "calendar/"

        const val LOGIN = ACCOUNT + "api/token/"
        const val GET_CALENDAR = CALENDAR + "api/get_calendar_events"
        const val STUDENT_PROFILE = STUDENT + "api/student_profile"

        const val STUDY_PLAN = GROUPS + "api/study_plan/"

        const val CREATE_REFERENCE = REFERENCES + "api/references/"

        const val GET_REF_HISTORY = CREATE_REFERENCE + "history"
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

    @FormUrlEncoded
    @POST(CREATE_REFERENCE)
    suspend fun createReference(
        @Header("Authorization") bearer: String,
        @Field("reference_id") type: Int
    ): User

    @GET(GET_CALENDAR)
    suspend fun getCalendar(
        @Header("Authorization") bearer: String
    ): AcademicCalendarDto

    @GET(GET_REF_HISTORY)
    suspend fun getRefHistory(
        @Header("Authorization") bearer: String
    ): List<RefHistory>

}