package org.example.project.ImagePickerRememberAPI

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import io.github.ismoy.imagepickerkmp.domain.config.CropConfig
import io.github.ismoy.imagepickerkmp.domain.config.GalleryConfig
import io.github.ismoy.imagepickerkmp.features.imagepicker.config.ImagePickerKMPConfig
import io.github.ismoy.imagepickerkmp.features.imagepicker.model.ImagePickerResult
import io.github.ismoy.imagepickerkmp.features.imagepicker.ui.rememberImagePickerKMP
import org.example.project.ImagePickerRememberAPI.ui.CameraResultCard
import org.example.project.ImagePickerRememberAPI.ui.MultiPhotoGrid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CropUsageRememberScreen(onBack: () -> Unit) {

    val picker = rememberImagePickerKMP(
        config = ImagePickerKMPConfig(
            cropConfig = CropConfig(
                enabled = true,
                aspectRatioLocked = false,
                circularCrop = true,
                squareCrop = true,
                freeformCrop = false
            ),
            galleryConfig = GalleryConfig(
                selectionLimit = 1
            )
        )
    )

    val result = picker.result

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Crop Usage") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Go Back"
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = { picker.launchCamera() },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Camera")
                    }
                    Button(
                        onClick = { picker.launchGallery() },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Gallery")
                    }
                }
            }
        }
    ) { scaffoldPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(scaffoldPadding),
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
                            text = "Processing crop...",
                            color = Color.Gray,
                            modifier = Modifier.padding(top = 12.dp)
                        )
                    }
                }

                is ImagePickerResult.Success -> {
                    val photos = result.photos
                    println("Valor Result: $photos")
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
                    Text(
                        text = "Press a button to take or select a photo with cropping",
                        color = Color.Gray,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}