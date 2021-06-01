package kz.iitu.campus.model.model

import java.util.*

data class RefHistory (
    val id:Int,
    val reference_type_id: RefType,
    val reference_file: String,
    val created_at: Date
        )

data class RefType(
    val id: Int,
    val name: String
)