package kz.iitu.campus.model.academic_calendar

data class AcademicCalendarDto(
    val Autumn: List<CalendarItem>,
    val Spring: List<CalendarItem>
)