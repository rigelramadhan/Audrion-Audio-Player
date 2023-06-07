package com.rivibi.audrion.core.data

import com.rivibi.audrion.core.data.local.LocalDataSource
import com.rivibi.audrion.core.domain.entity.Audio
import com.rivibi.audrion.core.domain.repository.IAudioRepository
import com.rivibi.audrion.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AudioRepository @Inject constructor(
    private val localDataSource: LocalDataSource
) : IAudioRepository {
    override fun getAudioList(): Flow<Resource<List<Audio>>> {
        return localDataSource.getAllAudioFiles()
            .map { Resource.Success(DataMapper.MapAudioEntityToDomain(it)) }
    }
}