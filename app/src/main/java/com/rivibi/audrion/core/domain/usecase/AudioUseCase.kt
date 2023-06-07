package com.rivibi.audrion.core.domain.usecase

import com.rivibi.audrion.core.data.Resource
import com.rivibi.audrion.core.domain.entity.Audio
import kotlinx.coroutines.flow.Flow

interface AudioUseCase {

    fun getAudioList(): Flow<Resource<List<Audio>>>
}