package kz.iitu.campus.model.academic_calendar

data class CalendarItem(
        val id: Long,
        val name: String,
        val from_date:  String,
        val to_date: String,
        val semester: String,
        val academic_calendar: AcademicCalendar
)