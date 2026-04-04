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
import androidx.compose.material.icons.filled.BrowseGallery
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.SwitchCamera
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.ismoy.imagepickerkmp.domain.config.CameraCallbacks
import io.github.ismoy.imagepickerkmp.domain.config.CameraCaptureConfig
import io.github.ismoy.imagepickerkmp.domain.config.GalleryConfig
import io.github.ismoy.imagepickerkmp.domain.config.UiConfig
import io.github.ismoy.imagepickerkmp.domain.models.CapturePhotoPreference
import io.github.ismoy.imagepickerkmp.domain.models.CompressionLevel
import io.github.ismoy.imagepickerkmp.features.imagepicker.config.ImagePickerKMPConfig
import io.github.ismoy.imagepickerkmp.features.imagepicker.model.ImagePickerResult
import io.github.ismoy.imagepickerkmp.features.imagepicker.ui.rememberImagePickerKMP

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CameraAndConfirmationDialogUiConfigRememberScreen(onBack: () -> Unit) {
    val picker = rememberImagePickerKMP(
        config = ImagePickerKMPConfig(
            cameraCaptureConfig = CameraCaptureConfig(
                captureButtonSize = 48.dp,
                preference = CapturePhotoPreference.BALANCED,
                compressionLevel = CompressionLevel.HIGH,
                uiConfig = UiConfig(
                    buttonColor = MaterialTheme.colorScheme.scrim,
                    iconColor = Color.White,
                    buttonSize = 48.dp,
                    flashIcon = Icons.Default.FlashOn,
                    switchCameraIcon = Icons.Default.SwitchCamera,
                    galleryIcon = Icons.Default.BrowseGallery
                ),
                cameraCallbacks = CameraCallbacks(
                    onCameraReady = { println("[ExtraConfig] Camera ready") },
                    onCameraSwitch = { println("[ExtraConfig] Camera switch") }
                )
            ),
            galleryConfig = GalleryConfig()
        )
    )

    val result = picker.result
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Only UIConfig Camera View And Confirmation View") },
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
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Camera")
                    }
                }
            }
        }
    ){scaffoldPadding->
        Column(
            modifier = Modifier
                .padding(scaffoldPadding)
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

                    is ImagePickerResult.Success -> {}

                    is ImagePickerResult.Error -> {}

                    is ImagePickerResult.Dismissed -> {}

                    is ImagePickerResult.Idle -> {}
                }
            }
        }
    }
}