package com.rivibi.audrion.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rivibi.audrion.ui.theme.AudrionTheme
import com.rivibi.audrion.ui.theme.Typography

@Composable
fun AudioItem(
    modifier: Modifier = Modifier,
    title: String,
    artist: String? = "Unknown",
    data: String,
    onAudioClick: () -> Unit,
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp)),
    ) {
        Row(modifier = modifier.padding(16.dp)) {
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(1f),
            ) {
                Text(
                    text = title,
                    style = Typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = artist ?: "Unknown",
                    style = Typography.bodySmall
                )
                Spacer(modifier = Modifier.height(8.dp))
                Divider(color = MaterialTheme.colorScheme.onSurface)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = data,
                    style = Typography.bodyMedium,
                    maxLines = 2,
                    modifier = Modifier.fillMaxWidth(),
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                onClick = onAudioClick
            ) {
                Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "Play Button")
            }
        }
    }
}

@Preview
@Composable
fun AudioItemPreview() {
    AudrionTheme {
        AudioItem(title = "Horizon Zero Dawn", artist = "GotY", data = "/storage/tempatnya") {

        }
    }
}