package kz.iitu.campus.pojo.auth

data class LoginResponse(
    val access: String,
    val refresh: String
)