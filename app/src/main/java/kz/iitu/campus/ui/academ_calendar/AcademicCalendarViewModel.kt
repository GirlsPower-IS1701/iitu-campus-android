package kz.iitu.campus.ui.academ_calendar

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kz.iitu.campus.pojo.academic_calendar.AcademicCalendar
import kz.iitu.campus.pojo.academic_calendar.CalendarItem

public class AcademicCalendarViewModel : ViewModel() {
    public val list = MutableLiveData<AcademicCalendar>()

    fun setList() {
        val autumnSemesterList = listOf(
            CalendarItem(0, "Enrollment in the number of students", "19.08.2020 - 24.08.2019"),
            CalendarItem(1, "Constitution day", "30.08.2020"),
            CalendarItem(2, "The day of the knowledge", "01.09.2020"),
            CalendarItem(3, "Fall term start", "02.09.2020"),
            CalendarItem(4, "Mid term exams", "14.10.2020 - 21.10.2020"),
            CalendarItem(5, "End of term exams", "09.12.2020 - 14.12.2020")
        )

        val springSemesterList = listOf(
            CalendarItem(0, "Enrollment in the number of students", "19.08.2020 - 24.08.2019"),
            CalendarItem(1, "Constitution day", "30.08.2020"),
            CalendarItem(2, "The day of the knowledge", "01.09.2020"),
            CalendarItem(3, "Fall term start", "02.09.2020"),
            CalendarItem(4, "Mid term exams", "14.10.2020 - 21.10.2020"),
            CalendarItem(5, "End of term exams", "09.12.2020 - 14.12.2020")
        )

        val dto = AcademicCalendar(
            id = 0,
            name = "IITU ACADEMIC CALENDAR 2020/21",
            autumnSemesterItems = autumnSemesterList,
            springSemesterItems = springSemesterList
        )

        list.value = dto
    }
}