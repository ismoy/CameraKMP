package org.example.project.ImagePickerRememberAPI

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.ismoy.imagepickerkmp.domain.config.CameraCaptureConfig
import io.github.ismoy.imagepickerkmp.domain.config.PermissionAndConfirmationConfig
import io.github.ismoy.imagepickerkmp.features.imagepicker.config.ImagePickerKMPConfig
import io.github.ismoy.imagepickerkmp.features.imagepicker.model.ImagePickerResult
import io.github.ismoy.imagepickerkmp.features.imagepicker.ui.rememberImagePickerKMP
import org.example.project.ImagePickerRememberAPI.ui.CameraResultCard
import org.example.project.ImagePickerRememberAPI.ui.CustomPermissionDeniedDialog
import org.example.project.ImagePickerRememberAPI.ui.CustomSettingsDialog

/**
 * EXAMPLE 3 – Custom permission dialogs with the new API.
 *
 * Demonstrates:
 *  - [PermissionAndConfirmationConfig.customDeniedDialog]   → custom UI when permission is denied
 *  - [PermissionAndConfirmationConfig.customSettingsDialog] → custom UI when permission is blocked
 *
 * Each dialog manages its own [visible] state internally with Dialog().
 * "Cancel" / "Not now" → visible = false → Dialog disappears immediately,
 * without depending on the library at all.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomPermissionRememberScreen( onBack: () -> Unit) {

    val picker = rememberImagePickerKMP(
        config = ImagePickerKMPConfig(
            cameraCaptureConfig = CameraCaptureConfig(
                permissionAndConfirmationConfig = PermissionAndConfirmationConfig(
                    customDeniedDialog = { onRetry ,onDismiss->
                        CustomPermissionDeniedDialog(
                            onRetry   = onRetry,
                            onDismiss = onDismiss
                        )
                    },
                    customSettingsDialog = { onOpenSettings,onDismiss ->
                        CustomSettingsDialog(
                            onOpenSettings = onOpenSettings,
                            onDismiss = onDismiss
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
                title = { Text("Custom Permission Dialog") },
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
                    onClick = { picker.launchCamera() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    Text("Open Camera (custom permission)")
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
                    Text(
                        "Custom permission dialogs example",
                        color = Color.Gray
                    )
                }
            }
        }
    }
}
