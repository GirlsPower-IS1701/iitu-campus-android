package kz.iitu.campus.model.schedule

data class Group(
    val id: Int,
    val name: String
) {
    override fun toString(): String {
        return name
    }
}

data class GroupDto(
    val group: String,
    val timetable: List<Timetable>
)