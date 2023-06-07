package com.rivibi.audrion.core.data.local

import android.content.ContentResolver
import android.provider.MediaStore
import com.rivibi.audrion.core.data.local.entity.AudioEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val contentResolver: ContentResolver) {

    fun getAllAudioFiles(): Flow<List<AudioEntity>> = flow {
        try {
            val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
            val projection = arrayOf(
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DATA,
            )

            val sortOrder = MediaStore.Audio.Media.TITLE

            val cursor = contentResolver.query(
                uri,
                projection,
                null,
                null,
                sortOrder
            )

            val audioList = mutableListOf<AudioEntity>()

            cursor?.use {
                val idColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
                val titleColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
                val artistColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
                val dataColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)

                while (it.moveToNext()) {
                    val id = it.getLong(idColumn)
                    val title = it.getString(titleColumn)
                    val artist = it.getString(artistColumn)
                    val data = it.getString(dataColumn)

                    audioList.add(
                        AudioEntity(
                            id, title, artist, data
                        )
                    )
                }
            }

            emit(audioList)
        } catch (e: Exception) {
            throw e
        }
    }
}