package kz.iitu.campus.model.schedule

data class Group(
    val id: Int,
    val name: String
) {
    override fun toString(): String {
        return name
    }
}