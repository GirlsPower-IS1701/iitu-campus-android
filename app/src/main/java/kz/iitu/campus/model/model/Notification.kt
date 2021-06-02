package kz.iitu.campus.model.model

import java.util.*

data class Notification(
    val id: Int,
    val title: String,
    val body: String,
    val created_at: Date
)