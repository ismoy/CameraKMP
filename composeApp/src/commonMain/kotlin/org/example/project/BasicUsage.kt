package org.example.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.ismoy.imagepickerkmp.domain.config.CameraCaptureConfig
import io.github.ismoy.imagepickerkmp.domain.config.ImagePickerConfig
import io.github.ismoy.imagepickerkmp.domain.config.PermissionAndConfirmationConfig
import io.github.ismoy.imagepickerkmp.domain.extensions.loadPainter
import io.github.ismoy.imagepickerkmp.domain.models.GalleryPhotoResult
import io.github.ismoy.imagepickerkmp.domain.models.PhotoResult
import io.github.ismoy.imagepickerkmp.presentation.ui.components.GalleryPickerLauncher
import io.github.ismoy.imagepickerkmp.presentation.ui.components.ImagePickerLauncher

@Composable
fun BasicUsage(
    innerPadding: PaddingValues,
    showImagePickerLauncher: Boolean,
    onShowImagePickerLauncherChange: (Boolean) -> Unit,
    showGalleryPickerLauncher: Boolean,
    onShowGalleryPickerLauncherChange: (Boolean) -> Unit,
    onPickerSheetIOSVisibleChange: (Boolean) -> Unit,
    selectedGalleryImages: List<GalleryPhotoResult>,
    onSelectedGalleryImagesChange: (List<GalleryPhotoResult>) -> Unit,
    resultImagePickerLauncher: PhotoResult?,
    onResultImagePickerLauncherChange: (PhotoResult?) -> Unit
){
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
            when {
                // launch ImagePickerLauncher
                showImagePickerLauncher -> {
                    ImagePickerLauncher(
                        config = ImagePickerConfig(
                            onPhotoCaptured = { result ->
                                onResultImagePickerLauncherChange(result)
                                onShowImagePickerLauncherChange(false)
                                onPickerSheetIOSVisibleChange(false)
                            },
                            onError = {
                                onShowImagePickerLauncherChange(false)
                                onPickerSheetIOSVisibleChange(false)
                            },
                            onDismiss = {
                                onShowImagePickerLauncherChange(false)
                                onPickerSheetIOSVisibleChange(false)
                            },
                            cameraCaptureConfig = CameraCaptureConfig(
                                permissionAndConfirmationConfig = PermissionAndConfirmationConfig(
                                    cancelButtonTextIOS = "Dismiss",
                                    onCancelPermissionConfigIOS = {
                                        onShowImagePickerLauncherChange(false)
                                    }
                                )
                            )
                        )
                    )
                }
                // launch GalleryPickerLauncher
                showGalleryPickerLauncher -> {
                    GalleryPickerLauncher(
                        onPhotosSelected = { result ->
                            onSelectedGalleryImagesChange(result)
                            onShowGalleryPickerLauncherChange(false)
                            onPickerSheetIOSVisibleChange(false)
                        },
                        onError = {
                            onShowGalleryPickerLauncherChange(false)
                            onPickerSheetIOSVisibleChange(false)
                        },
                        onDismiss = {
                            onShowGalleryPickerLauncherChange(false)
                            onPickerSheetIOSVisibleChange(false)
                        }
                    )
                }
                // show list image selected to the Gallery
                selectedGalleryImages.isNotEmpty() -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(8.dp)
                    ) {
                        items(selectedGalleryImages) { photo ->
                            Card(
                                modifier = Modifier
                                    .padding(6.dp)
                                    .fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp),
                            ) {
                                photo.loadPainter()?.let {
                                    Image(
                                        painter = it,
                                        contentDescription = "Selected image",
                                        modifier = Modifier.fillMaxSize()
                                    )
                                }
                            }
                        }
                    }
                }

                resultImagePickerLauncher != null -> {
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    ) {
                        resultImagePickerLauncher.loadPainter()?.let {
                            Image(
                                painter = it,
                                contentDescription = "Captured photo",
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }

                else -> {
                    Text("No image selected", color = Color.Gray)
                }

            }

        }
    }
}