package kz.iitu.campus.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kz.iitu.campus.model.schedule.Group
import kz.iitu.campus.model.schedule.GroupDto
import kz.iitu.campus.model.schedule.Staff
import kz.iitu.campus.model.schedule.Timetable
import kz.iitu.campus.services.ApiClient


class ScheduleRepository (
    private val apiClient: ApiClient
) {

    suspend fun getGroupList(bearer: String): List<Group> {
        return withContext(Dispatchers.IO) {
            apiClient.getGroupList(bearer)
        }
    }

    suspend fun getStaffList(bearer: String): List<Staff> {
        return withContext(Dispatchers.IO) {
            apiClient.getStaffList(bearer)
        }
    }

    suspend fun getGroupTimetable(bearer: String): List<Timetable> {
        return withContext(Dispatchers.IO) {
            apiClient.getTimetable(bearer)
        }
    }

    suspend fun getGroupTimetableByStaff(bearer: String, id: Int): List<Timetable> {
        return withContext(Dispatchers.IO) {
            apiClient.getTimetableByStaff(bearer, id)
        }
    }

    suspend fun getGroupTimetableByGroup(bearer: String, id: Int): GroupDto {
        return withContext(Dispatchers.IO) {
            apiClient.getTimetableByGroup(bearer, id)
        }
    }

    suspend fun getGroupTimetableByRoom(bearer: String, room: Int): List<Timetable> {
        return withContext(Dispatchers.IO) {
            apiClient.getTimetableByRoom(bearer, room)
        }
    }

}
