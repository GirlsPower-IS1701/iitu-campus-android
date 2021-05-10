package kz.iitu.campus.model.model

import com.google.gson.annotations.SerializedName

data class Student(
    private val user: User,
    @SerializedName("study_status")
    private val studyStatus: StudyStatus,
    @SerializedName("study_form")
    private val studyForm: StudyForm,
    @SerializedName("payment_form")
    private val paymentForm: PaymentForm,
    @SerializedName("degree_type")
    private val degreeType: StudyStatus,
    private val course: Int,
    private val deleted: Boolean,
    @SerializedName("created_at")
    private val createdDate: String
)

data class StudyForm(
    private val description: String,
    @SerializedName("course_count")
    private val courseCount: Int,
    @SerializedName("created_at")
    private val createdDate: String,
    private val deleted: Boolean
)

data class PaymentForm(
    private val description: String,
    @SerializedName("created_at")
    private val createdDate: String,
    private val deleted: Boolean
)

data class StudyStatus(
    private val title: String,
    @SerializedName("created_at")
    private val createdDate: String,
    private val deleted: Boolean
)