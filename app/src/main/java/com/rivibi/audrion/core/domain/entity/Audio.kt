package com.rivibi.audrion.core.domain.entity

data class Audio(
    val id: Long,
    val title: String,
    val artist: String? = null,
    val data: String,
)