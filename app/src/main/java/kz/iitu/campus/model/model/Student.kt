package kz.iitu.campus.model.model

import com.google.gson.annotations.SerializedName

data class Student(
     val user: User,
    @SerializedName("study_status")
     val studyStatus: StudyStatus,
    @SerializedName("study_form")
     val studyForm: StudyForm,
    @SerializedName("payment_form")
     val paymentForm: PaymentForm,
    @SerializedName("degree_type")
     val degreeType: StudyStatus,
     val course: Int,
     val deleted: Boolean,
    @SerializedName("created_at")
     val createdDate: String
)

data class StudyForm(
     val description: String,
    @SerializedName("course_count")
     val courseCount: Int,
    @SerializedName("created_at")
     val createdDate: String,
     val deleted: Boolean
)

data class PaymentForm(
     val description: String,
    @SerializedName("created_at")
     val createdDate: String,
     val deleted: Boolean
)

data class StudyStatus(
     val title: String,
    @SerializedName("created_at")
     val createdDate: String,
     val deleted: Boolean
)