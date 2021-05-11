package kz.iitu.campus.model.model

import com.google.gson.annotations.SerializedName

data class StudentProfile(
    val student: Student,
    val avatar: String,
    @SerializedName("created_at")
    val createdDate: String,
    val deleted: Boolean
)