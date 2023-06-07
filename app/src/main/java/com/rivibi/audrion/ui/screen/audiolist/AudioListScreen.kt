package com.rivibi.audrion.ui.screen.audiolist

import android.media.MediaPlayer
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rivibi.audrion.R
import com.rivibi.audrion.core.data.Resource
import com.rivibi.audrion.core.domain.entity.Audio
import com.rivibi.audrion.ui.components.AudioItem

@Composable
fun AudioListScreen(
    modifier: Modifier = Modifier,
    viewModel: AudioListViewModel = hiltViewModel()
) {
    val uiState: Resource<List<Audio>> by viewModel.uiState.collectAsState()
    val mediaPlayer = remember { MediaPlayer() }
    val permissionState = remember { mutableStateOf(false) }

    val requestPermissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            permissionState.value = isGranted
        }

    when (uiState) {
        is Resource.Success -> {
            val data = uiState.data
            if (data.isNullOrEmpty()) {
                Toast.makeText(
                    LocalContext.current,
                    stringResource(id = R.string.no_audio_found),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                AudioListContent(
                    audioList = data,
                    modifier = modifier,
                ) { audioData ->
                    requestPermissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)

                    if (permissionState.value) {
                        mediaPlayer.reset()
                        mediaPlayer.setDataSource(audioData)
                        mediaPlayer.prepare()
                        mediaPlayer.start()
                    }
                }
            }
        }

        is Resource.Error -> {

        }

        is Resource.Loading -> {

        }
    }
}

@Composable
fun AudioListContent(
    audioList: List<Audio>,
    modifier: Modifier = Modifier,
    onAudioClick: (String) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        items(audioList) {
            AudioItem(
                title = it.title,
                artist = it.artist,
                modifier = Modifier.fillMaxWidth(),
                data = it.data,
                onAudioClick = { onAudioClick(it.data) }
            )
        }
    }
}