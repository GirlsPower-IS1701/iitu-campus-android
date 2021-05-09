package kz.iitu.campus.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kz.iitu.campus.pojo.schedule.ScheduleDto
import kz.iitu.campus.pojo.schedule.ScheduleItem

class HomeViewModel : ViewModel() {
    val list = MutableLiveData<ScheduleDto>()

    fun setList() {
        val lessonListVersion1 = listOf(
            ScheduleItem(
                0, "11:00", "11:50",
                "Fundamentals of Scientific Research", "700", "IS-1701K,IS-1702K",
                "Aitim A.K.", "senior-lecturer"
            ),
            ScheduleItem(
                1, "12:10", "13:00",
                "Fundamentals of Scientific Research", "705", "IS-1701K",
                "Aitim A.K.", "senior-lecturer"
            ),
            ScheduleItem(
                2, "13:10", "14:00",
                "Fundamentals of Scientific Research", "605", "IS-1701K,",
                "Aitim A.K.", "senior-lecturer"
            )
        )

        val lessonListVersion2 = listOf(
            ScheduleItem(
                0, "11:00", "11:50",
                "Database and Client/Server Application", "700", "IS-1701K,IS-1702K",
                "Muratova K.", "senior-lecturer"
            ),
            ScheduleItem(
                1, "12:10", "13:00",
                "Fundamentals of Scientific Research", "705", "IS-1701K",
                "Muratova K.", "senior-lecturer"
            ),
            ScheduleItem(
                2, "13:10", "14:00",
                "Fundamentals of Scientific Research", "605", "IS-1701K,",
                "Muratova K.", "senior-lecturer"
            )
        )

        val scheduleDto = ScheduleDto(
            id = 0,
            group = "IS-1701K",
            mondayList = lessonListVersion1,
            thurthdayList = lessonListVersion2,
            tuesdayList = lessonListVersion1,
            fridayList = lessonListVersion1,
            wednesdayList = lessonListVersion2
        )

        list.value = scheduleDto
    }
}