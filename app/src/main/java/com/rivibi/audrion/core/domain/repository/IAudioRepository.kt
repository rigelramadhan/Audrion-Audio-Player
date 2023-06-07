package com.rivibi.audrion.core.domain.repository

import com.rivibi.audrion.core.domain.entity.Audio
import kotlinx.coroutines.flow.Flow

interface IAudioRepository {

    fun getAudioList(): Flow<List<Audio>>
}