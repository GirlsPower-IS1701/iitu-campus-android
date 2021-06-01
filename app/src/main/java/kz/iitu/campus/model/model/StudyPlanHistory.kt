package kz.iitu.campus.model.model

import java.util.*

data class StudyPlanHistory(
    val id : Int,
    val user: User,
    val gpa_file: String,
    val created_at: Date
)