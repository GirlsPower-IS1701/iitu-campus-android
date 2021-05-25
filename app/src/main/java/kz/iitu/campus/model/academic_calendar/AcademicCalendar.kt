package kz.iitu.campus.model.academic_calendar

data class AcademicCalendar(
        val id: Long,
        val name: String,
        val from_year:Long,
        val to_year: Long
)