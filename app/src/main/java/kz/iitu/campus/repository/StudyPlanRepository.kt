package kz.iitu.campus.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kz.iitu.campus.model.model.StudyPlanDto
import kz.iitu.campus.model.model.StudyPlanHistory
import kz.iitu.campus.model.model.User
import kz.iitu.campus.services.ApiClient

class StudyPlanRepository(
    private val apiClient: ApiClient
) {

    suspend fun getStudyPlan(bearer: String): StudyPlanDto {
        return withContext(Dispatchers.IO) {
            apiClient.getStudyPlan(bearer)
        }
    }

    suspend fun createTranscript(bearer: String): User {
        return withContext(Dispatchers.IO) {
            apiClient.createTranscript(bearer)
        }
    }

    suspend fun getTranscriptHistory(bearer: String): List<StudyPlanHistory> {
        return withContext(Dispatchers.IO) {
            apiClient.getTranscriptHistory(bearer)
        }
    }


}