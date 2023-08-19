package com.i.records_impl.core

import kotlinx.serialization.Serializable

@Serializable
data class RecordResponse(
    val id: Long,
    val date: String,
    val pressure: String,
    val feelings: String
)
