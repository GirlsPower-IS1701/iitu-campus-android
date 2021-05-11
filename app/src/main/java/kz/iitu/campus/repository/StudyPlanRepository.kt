package kz.iitu.campus.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kz.iitu.campus.model.model.StudyPlan
import kz.iitu.campus.services.ApiClient

class StudyPlanRepository(
    private val apiClient: ApiClient
) {

    suspend fun getStudyPlan(bearer: String): List<StudyPlan> {
        return withContext(Dispatchers.IO) {
            apiClient.getStudyPlan(bearer)
        }
    }
}