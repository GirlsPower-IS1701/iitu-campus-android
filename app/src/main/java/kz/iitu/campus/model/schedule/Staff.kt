package kz.iitu.campus.model.schedule

data class Staff(
    val id: Int,
    val name: String,
    val surname: String
) {
    override fun toString(): String {
        return name + " " + surname
    }
}