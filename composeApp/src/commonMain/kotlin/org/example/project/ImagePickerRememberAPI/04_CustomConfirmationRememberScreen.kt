package org.example.project.ImagePickerRememberAPI

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.ismoy.imagepickerkmp.domain.config.CameraCaptureConfig
import io.github.ismoy.imagepickerkmp.domain.config.PermissionAndConfirmationConfig
import io.github.ismoy.imagepickerkmp.features.imagepicker.config.ImagePickerKMPConfig
import io.github.ismoy.imagepickerkmp.features.imagepicker.model.ImagePickerResult
import io.github.ismoy.imagepickerkmp.features.imagepicker.ui.rememberImagePickerKMP
import org.example.project.ImagePickerRememberAPI.ui.CameraResultCard
import org.example.project.ImagePickerRememberAPI.ui.CustomConfirmationView

/**
 * EXAMPLE 4 – Custom confirmation view on Android.
 *
 * Demonstrates:
 *  - [PermissionAndConfirmationConfig.customConfirmationView] → replaces the default
 *    "confirm photo" screen from the library with a custom UI
 *  - [PermissionAndConfirmationConfig.skipConfirmation]       → disables confirmation
 *    (we show the alternative button for comparison)
 *
 * The confirmation view is in a separate file:
 *  - [CustomConfirmationView] → ui/CustomConfirmationView.kt
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomConfirmationRememberScreen(innerPadding: PaddingValues, onBack: () -> Unit) {

    val picker = rememberImagePickerKMP(
        config = ImagePickerKMPConfig(
            cameraCaptureConfig = CameraCaptureConfig(
                permissionAndConfirmationConfig = PermissionAndConfirmationConfig(
                    skipConfirmation = false,
                    customConfirmationView = { photoResult, onConfirm, onRetry ->
                        CustomConfirmationView(
                            result = photoResult,
                            onConfirm = onConfirm,
                            onRetry = onRetry
                        )
                    }
                )
            )
        )
    )

    val result = picker.result
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Custom confirmationView only android") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar {
                Button(
                    onClick = { picker.launchCamera() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    Text("Open Camera (custom confirmation")
                }
            }
        }
    ) { scaffoldPadding ->

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
                        CircularProgressIndicator()
                    }

                    is ImagePickerResult.Success -> {
                        CameraResultCard(photo = result.photos.first())
                    }

                    is ImagePickerResult.Error -> {
                        Text(
                            "Error: ${result.exception.message}",
                            color = Color.Red,
                            modifier = Modifier.padding(16.dp)
                        )
                    }

                    is ImagePickerResult.Dismissed -> {
                        Text("Cancelled", color = Color.Gray)
                    }

                    is ImagePickerResult.Idle -> {
                        Text("Custom confirmation example", color = Color.Gray)
                    }
                }
            }
        }
    }
}
