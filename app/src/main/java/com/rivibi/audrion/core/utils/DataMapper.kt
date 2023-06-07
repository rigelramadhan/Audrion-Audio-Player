package com.rivibi.audrion.core.utils

import com.rivibi.audrion.core.data.local.entity.AudioEntity
import com.rivibi.audrion.core.domain.entity.Audio

object DataMapper {
    fun MapAudioEntityToDomain(input: List<AudioEntity>): List<Audio> = input.map {
        Audio(
            id = it.id,
            title = it.title,
            artist = it.artist,
            data = it.data
        )
    }
}