package kz.iitu.campus.pojo.schedule

import java.util.*

data class ScheduleItem(
        val id: Long,
        val fromTime: String,
        val toTime: String,
        val subjectName: String,
        val room: String,
        val group: String,
        val teacher_name: String,
        val teacher_position: String
)