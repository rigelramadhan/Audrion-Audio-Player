package com.rivibi.audrion.core.data.local.entity

data class AudioEntity(
    val id: Long,
    val title: String,
    val artist: String? = null,
    val data: String,
)