package kz.iitu.campus.model.model

import com.google.gson.annotations.SerializedName

data class StudentProfile(
    private val student: Student,
    private val avatar: String,
    @SerializedName("created_at")
    private val createdDate: String,
    private val deleted: Boolean
)