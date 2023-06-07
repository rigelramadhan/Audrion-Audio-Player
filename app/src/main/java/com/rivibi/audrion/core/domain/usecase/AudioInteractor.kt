package com.rivibi.audrion.core.domain.usecase

import com.rivibi.audrion.core.data.Resource
import com.rivibi.audrion.core.domain.entity.Audio
import com.rivibi.audrion.core.domain.repository.IAudioRepository
import kotlinx.coroutines.flow.Flow

class AudioInteractor(private val audioRepository: IAudioRepository) : AudioUseCase {
    override fun getAudioList(): Flow<Resource<List<Audio>>> = audioRepository.getAudioList()
}