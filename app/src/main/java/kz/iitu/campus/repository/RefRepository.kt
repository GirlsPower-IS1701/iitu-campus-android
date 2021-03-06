package kz.iitu.campus.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kz.iitu.campus.model.model.RefHistory
import kz.iitu.campus.model.model.User
import kz.iitu.campus.services.ApiClient

class RefRepository(
    private val apiClient: ApiClient
) {
    suspend fun createRef(bearer: String): User {
        return withContext(Dispatchers.IO) {
            apiClient.createReference(bearer, 1)
        }
    }

    suspend fun getRefHistory(bearer: String): List<RefHistory> {
        return withContext(Dispatchers.IO) {
            apiClient.getRefHistory(bearer)
        }
    }
}