package kz.iitu.campus.pojo.academic_calendar

data class AcademicCalendar(
        val id: Long,
        val name: String,
        val autumnSemesterItems: List<CalendarItem>,
        val springSemesterItems: List<CalendarItem>
)