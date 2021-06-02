package kz.iitu.campus.model.schedule

data class Timetable (
    val day_of_week: Int,
    val lesson_type: String,
    val subject: String,
    val teacher_name: String,
    val teacher_surname: String,
    val start_time: String,
    val finish_time: String,
    val room_number: String
        )