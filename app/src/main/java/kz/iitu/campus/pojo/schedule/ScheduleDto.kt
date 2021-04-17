package kz.iitu.campus.pojo.schedule

data class ScheduleDto(
        val id: Long,
        val group: String,
        val mondayList: List<ScheduleItem>,
        val tuesdayList: List<ScheduleItem>,
        val wednesdayList: List<ScheduleItem>,
        val thurthdayList: List<ScheduleItem>,
        val fridayList: List<ScheduleItem>

)