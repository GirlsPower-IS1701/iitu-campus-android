package kz.iitu.campus.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kz.iitu.campus.model.model.LoginResponse
import kz.iitu.campus.services.ApiClient

class AuthRepository(
    private val apiClient: ApiClient
) {
    suspend fun login(username: String, pass: String): LoginResponse? {
        return withContext(Dispatchers.IO) {
            apiClient.login(username, pass)
        }
    }
}