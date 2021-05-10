package kz.iitu.campus.model.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("first_name")
    private val firstName: String,
    @SerializedName("last_name")
    private val lastName: String,
    @SerializedName("username")
    private val userName: String,
    @SerializedName("email")
    private val email: String,
    @SerializedName("phone_number")
    private val phoneNumber: String
)