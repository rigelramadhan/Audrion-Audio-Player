package com.rivibi.audrion.ui.screen.audiolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rivibi.audrion.core.data.Resource
import com.rivibi.audrion.core.domain.entity.Audio
import com.rivibi.audrion.core.domain.usecase.AudioUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AudioListViewModel @Inject constructor(
    private val audioUseCase: AudioUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<Resource<List<Audio>>> =
        MutableStateFlow(Resource.Loading())
    val uiState: StateFlow<Resource<List<Audio>>> get() = _uiState

    init {
        viewModelScope.launch {
            audioUseCase.getAudioList()
                .catch {
                    _uiState.value = Resource.Error(it.message.toString())
                }
                .collect { data ->
                    _uiState.value = data
                }
        }
    }
}