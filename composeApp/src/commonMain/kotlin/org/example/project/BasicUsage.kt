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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.ismoy.imagepickerkmp.domain.config.CameraCaptureConfig
import io.github.ismoy.imagepickerkmp.domain.config.ImagePickerConfig
import io.github.ismoy.imagepickerkmp.domain.config.PermissionAndConfirmationConfig
import io.github.ismoy.imagepickerkmp.domain.extensions.loadPainter
import io.github.ismoy.imagepickerkmp.domain.models.CompressionLevel
import io.github.ismoy.imagepickerkmp.domain.models.GalleryPhotoResult
import io.github.ismoy.imagepickerkmp.domain.models.PhotoResult
import io.github.ismoy.imagepickerkmp.presentation.ui.components.GalleryPickerLauncher
import io.github.ismoy.imagepickerkmp.presentation.ui.components.ImagePickerLauncher
import kotlinx.coroutines.delay

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
) {
    var isLoading by remember { mutableStateOf(false) }
    var isLoadingContent by remember { mutableStateOf(false) }

    LaunchedEffect(selectedGalleryImages.size, resultImagePickerLauncher) {
        if (selectedGalleryImages.isNotEmpty() || resultImagePickerLauncher != null) {
            isLoadingContent = true
            delay(300)
            isLoadingContent = false
        }
    }

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
                showImagePickerLauncher -> {
                    isLoading = true
                    ImagePickerLauncher(
                        config = ImagePickerConfig(
                            onPhotoCaptured = { result ->
                                onResultImagePickerLauncherChange(result)
                                onShowImagePickerLauncherChange(false)
                                onPickerSheetIOSVisibleChange(false)
                                isLoading = false
                            },
                            onError = {
                                onShowImagePickerLauncherChange(false)
                                onPickerSheetIOSVisibleChange(false)
                                isLoading = false
                            },
                            onDismiss = {
                                onShowImagePickerLauncherChange(false)
                                onPickerSheetIOSVisibleChange(false)
                                isLoading = false
                            },
                            cameraCaptureConfig = CameraCaptureConfig(
                                permissionAndConfirmationConfig = PermissionAndConfirmationConfig(
                                    cancelButtonTextIOS = "Dismiss",
                                    onCancelPermissionConfigIOS = {
                                        onShowImagePickerLauncherChange(false)
                                        isLoading = false
                                    }
                                )
                            ),
                            directCameraLaunch = true
                        )
                    )
                }
                showGalleryPickerLauncher -> {
                    isLoading = true
                    GalleryPickerLauncher(
                        onPhotosSelected = { result ->
                            onSelectedGalleryImagesChange(result)
                            onShowGalleryPickerLauncherChange(false)
                            onPickerSheetIOSVisibleChange(false)
                            isLoading = false
                        },
                        onError = {
                            onShowGalleryPickerLauncherChange(false)
                            onPickerSheetIOSVisibleChange(false)
                            isLoading = false
                        },
                        onDismiss = {
                            onShowGalleryPickerLauncherChange(false)
                            onPickerSheetIOSVisibleChange(false)
                            isLoading = false
                        },
                        allowMultiple = true,
                        cameraCaptureConfig = CameraCaptureConfig(
                            compressionLevel = CompressionLevel.HIGH
                        )
                    )
                }

                isLoadingContent -> {
                    CircularProgressIndicator()
                }

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
                                val painter = photo.loadPainter()
                                if (painter != null) {
                                    Image(
                                        painter = painter,
                                        contentDescription = "Selected image",
                                        modifier = Modifier.fillMaxSize()
                                    )
                                } else {
                                    CircularProgressIndicator()
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
                        val painter = resultImagePickerLauncher.loadPainter()
                        if (painter != null) {
                            Image(
                                painter = painter,
                                contentDescription = "Captured photo",
                                modifier = Modifier.fillMaxSize()
                            )
                        } else {
                            CircularProgressIndicator()
                        }
                    }
                }

                isLoading -> {
                    CircularProgressIndicator()
                }

                else -> {
                    Text("No image selected", color = Color.Gray)
                }
            }
        }
    }
}