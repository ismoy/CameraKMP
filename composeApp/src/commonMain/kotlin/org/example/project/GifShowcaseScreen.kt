package org.example.project

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import io.github.ismoy.imagepickerkmp.domain.config.GalleryConfig
import io.github.ismoy.imagepickerkmp.domain.models.MimeType
import io.github.ismoy.imagepickerkmp.features.imagepicker.config.ImagePickerKMPConfig
import io.github.ismoy.imagepickerkmp.features.imagepicker.model.ImagePickerResult
import io.github.ismoy.imagepickerkmp.features.imagepicker.ui.rememberImagePickerKMP

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GifShowcaseScreen(onBack: () -> Unit) {
    val picker = rememberImagePickerKMP(
        config = ImagePickerKMPConfig(
            galleryConfig = GalleryConfig(
                mimeTypes = listOf(MimeType.IMAGE_GIF)
            )
        )
    )
    val result = picker.result

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("GIF Showcase") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Go back"
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar {
                Button(
                    onClick = { picker.launchGallery() },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                ) {
                    Text("Select GIF")
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            when (result) {
                is ImagePickerResult.Success -> {
                    val uri = result.photos.firstOrNull()?.uri
                    if (uri != null) {
                        AnimatedGifImage(
                            uri = uri,
                            contentDescription = "Selected GIF",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Fit
                        )
                    }
                }
                is ImagePickerResult.Loading -> CircularProgressIndicator()
                is ImagePickerResult.Error -> Text("Error: ${result.exception.message}")
                is ImagePickerResult.Dismissed -> Text("No GIF selected")
                is ImagePickerResult.Idle -> Text("Tap 'Select GIF' to pick one")
            }
        }
    }
}

