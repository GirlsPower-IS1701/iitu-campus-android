package kz.iitu.campus.pojo.academic_calendar

import java.util.*

data class CalendarItem(
        val id: Long,
        val name: String,
        val toDate: String,
        val fromDate:  String
)