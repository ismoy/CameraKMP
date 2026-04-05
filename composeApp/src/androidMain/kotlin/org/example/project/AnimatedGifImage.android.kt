package org.example.project

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage

@Composable
actual fun AnimatedGifImage(
    uri: String,
    contentDescription: String,
    modifier: Modifier,
    contentScale: ContentScale
) {
    // Coil on Android supports GIF animation via GifDecoder
    // configured in MainActivity.kt with SingletonImageLoader
    AsyncImage(
        model = uri,
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale
    )
}
