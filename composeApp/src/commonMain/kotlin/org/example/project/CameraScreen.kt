package org.example.project

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.ismoy.imagepickerkmp.domain.models.GalleryPhotoResult
import io.github.ismoy.imagepickerkmp.domain.models.PhotoResult
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CameraScreen(onBack: () -> Unit = {}) {
    var showImagePickerLauncher by remember { mutableStateOf(false) }
    var showGalleryPickerLauncher by remember { mutableStateOf(false) }
    var isPickerSheetIOSVisible by remember { mutableStateOf(false) }
    var selectedGalleryImages by remember { mutableStateOf<List<GalleryPhotoResult>>(emptyList()) }
    var resultImagePickerLauncher by remember { mutableStateOf<PhotoResult?>(null) }
    var isOCRActive by remember { mutableStateOf(false) }
    var isInOCRScreen by remember { mutableStateOf(false) }
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Legacy API") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back to home"
                        )
                    }
                }
            )
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (isOCRActive || isInOCRScreen) {
                    OutlinedButton(
                        onClick = {
                            isOCRActive = true
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Text("Test OCR", color = Color.Blue)
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                } else {
                    if (!isPickerSheetIOSVisible) {
                        OutlinedButton(
                            onClick = {
                                selectedGalleryImages = emptyList()
                                resultImagePickerLauncher = null
                                showBottomSheet = true // Mostrar BottomSheet con opciones
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        ) {
                            Text("Seleccionar Imagen")
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedButton(
                            onClick = {
                                isOCRActive = true
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        ) {
                            Text("Test OCR", color = Color.Blue)
                        }

                        Spacer(modifier = Modifier.height(30.dp))
                    }
                }
            }
        }
    ) { innerPadding ->
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState,
                dragHandle = { BottomSheetDefaults.DragHandle() }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Seleccionar imagen",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    
                    TextButton(
                        onClick = {
                            scope.launch {
                                sheetState.hide()
                                showBottomSheet = false
                                showGalleryPickerLauncher = true
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Galería")
                    }
                    
                    HorizontalDivider(Modifier.height(1.dp))
                    
                    TextButton(
                        onClick = {
                            scope.launch {
                                sheetState.hide()
                                showBottomSheet = false
                                showImagePickerLauncher = true
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Tomar foto")
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }

       // OCRUsageScreen(innerPadding, isOCRActive, onOCRActiveChange = { isOCRActive = it })

        BasicUsage(
            innerPadding = innerPadding,
            showImagePickerLauncher = showImagePickerLauncher,
            onShowImagePickerLauncherChange = { showImagePickerLauncher = it },
            showGalleryPickerLauncher = showGalleryPickerLauncher,
            onShowGalleryPickerLauncherChange = { showGalleryPickerLauncher = it },
            onPickerSheetIOSVisibleChange = { isPickerSheetIOSVisible = it },
            selectedGalleryImages = selectedGalleryImages,
            onSelectedGalleryImagesChange = { selectedGalleryImages = it },
            resultImagePickerLauncher = resultImagePickerLauncher,
            onResultImagePickerLauncherChange = { resultImagePickerLauncher = it }
        )
       /* CustomPermissionDialog(
            innerPadding = innerPadding,
            showImagePickerLauncher = showImagePickerLauncher,
            onShowImagePickerLauncherChange = { showImagePickerLauncher = it },
            onPickerSheetIOSVisibleChange = { isPickerSheetIOSVisible = it },
            resultImagePickerLauncher = resultImagePickerLauncher,
            onResultImagePickerLauncherChange = { resultImagePickerLauncher = it }
        )*/

       /* CustomConfirmationViewAndroid(
            innerPadding = innerPadding,
            showImagePickerLauncher = showImagePickerLauncher,
            onShowImagePickerLauncherChange = { showImagePickerLauncher = it },
            onPickerSheetIOSVisibleChange = { isPickerSheetIOSVisible = it },
            resultImagePickerLauncher = resultImagePickerLauncher,
            onResultImagePickerLauncherChange = { resultImagePickerLauncher = it }

        )*/
        /*CustomBottomSheetIOS(
            innerPadding = innerPadding,
            showImagePickerLauncher = showImagePickerLauncher,
            onShowImagePickerLauncherChange = { showImagePickerLauncher = it },
            onPickerSheetIOSVisibleChange = { isPickerSheetIOSVisible = it },
            resultImagePickerLauncher = resultImagePickerLauncher,
            onResultImagePickerLauncherChange = { resultImagePickerLauncher = it }
        )*/

      /* ExtraConfig(
            innerPadding = innerPadding,
            showImagePickerLauncher = showImagePickerLauncher,
            onShowImagePickerLauncherChange = { showImagePickerLauncher = it },
            showGalleryPickerLauncher = showGalleryPickerLauncher,
            onShowGalleryPickerLauncherChange = { showGalleryPickerLauncher = it },
            onPickerSheetIOSVisibleChange = { isPickerSheetIOSVisible = it },
            selectedGalleryImages = selectedGalleryImages,
            onSelectedGalleryImagesChange = { selectedGalleryImages = it },
            resultImagePickerLauncher = resultImagePickerLauncher,
            onResultImagePickerLauncherChange = { resultImagePickerLauncher = it }
        )*/

    }
}