package kz.iitu.campus.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kz.iitu.campus.model.academic_calendar.AcademicCalendarDto
import kz.iitu.campus.services.ApiClient

class AcademicCalendarRepository(
    private val apiClient: ApiClient
) {

    suspend fun getCalendar(bearer: String): AcademicCalendarDto {
        return withContext(Dispatchers.IO) {
            apiClient.getCalendar(bearer)
        }
    }
}