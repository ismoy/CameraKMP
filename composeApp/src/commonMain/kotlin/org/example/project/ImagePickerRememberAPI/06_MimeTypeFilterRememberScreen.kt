package org.example.project.ImagePickerRememberAPI

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.ismoy.imagepickerkmp.domain.config.GalleryConfig
import io.github.ismoy.imagepickerkmp.domain.models.MimeType
import io.github.ismoy.imagepickerkmp.features.imagepicker.config.ImagePickerKMPConfig
import io.github.ismoy.imagepickerkmp.features.imagepicker.model.ImagePickerResult
import io.github.ismoy.imagepickerkmp.features.imagepicker.ui.rememberImagePickerKMP
import org.example.project.ImagePickerRememberAPI.ui.CameraResultCard
import org.example.project.ImagePickerRememberAPI.ui.MultiPhotoGrid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MimeTypeFilterRememberScreen(onBack: () -> Unit){

    val picker = rememberImagePickerKMP(
        config = ImagePickerKMPConfig(
            galleryConfig = GalleryConfig(
                allowMultiple = true,
                selectionLimit = 10,
                mimeTypes = listOf(MimeType.IMAGE_ALL),
                mimeTypeMismatchMessage = "Please only select PNG images"
            ),
        )
    )
    val result = picker.result
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("MimeType filter") },
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
            BottomAppBar{
                Button(
                    onClick = { picker.launchGallery() },
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 24.dp)
                ) {
                    Text("Gallery")
                }
            }
        }
    ) {  innerPadding->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                when (result) {

                    is ImagePickerResult.Loading -> {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(16.dp)
                        ) {
                            CircularProgressIndicator()
                            Text(
                                text = "Loading...",
                                color = Color.Gray,
                                modifier = Modifier.padding(top = 12.dp)
                            )
                        }
                    }

                    is ImagePickerResult.Success -> {
                        val photos = result.photos
                        if (photos.size == 1) {
                            CameraResultCard(photo = photos.first())
                        } else {
                            MultiPhotoGrid(photos = photos)
                        }
                    }

                    is ImagePickerResult.Error -> {
                        Text(
                            text = "Error: ${result.exception.message}",
                            color = Color.Red,
                            modifier = Modifier.padding(16.dp)
                        )
                    }

                    is ImagePickerResult.Dismissed -> {
                        Text("Selection cancelled", color = Color.Gray)
                    }

                    is ImagePickerResult.Idle -> {
                        Text("Press a button to get started", color = Color.Gray)
                    }
                }
            }
        }

    }
}