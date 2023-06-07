package com.rivibi.audrion.core.di

import com.rivibi.audrion.core.data.AudioRepository
import com.rivibi.audrion.core.domain.repository.IAudioRepository
import com.rivibi.audrion.core.domain.usecase.AudioInteractor
import com.rivibi.audrion.core.domain.usecase.AudioUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(audioRepository: AudioRepository): IAudioRepository = audioRepository

    @Provides
    fun provideUseCase(repository: IAudioRepository): AudioUseCase = AudioInteractor(repository)
}