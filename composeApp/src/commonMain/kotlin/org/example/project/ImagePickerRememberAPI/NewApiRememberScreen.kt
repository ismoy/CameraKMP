package org.example.project.ImagePickerRememberAPI

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.Crop
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.PhotoLibrary
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import org.example.project.isAndroid

private enum class ExampleScreen {
    Basic, Permission, Confirmation, Crop, UiConfig,GalleryMimeType
}

private data class ExampleItem(
    val screen: ExampleScreen,
    val icon: ImageVector,
    val tag: String,
    val title: String,
    val description: String,
    val bulletPoints: List<String>,
    val warning: String? = null
)

private val examples = listOf(
    ExampleItem(
        screen      = ExampleScreen.Basic,
        icon        = Icons.Outlined.CameraAlt,
        tag         = "launchCamera · launchGallery",
        title       = "Basic Usage",
        description = "Minimal setup to capture a photo or pick from the gallery.",
        bulletPoints = listOf(
            "Camera capture with result callback",
            "Gallery picker — multiple selection up to 5",
            "All result states: Idle, Loading, Success, Error, Dismissed"
        )
    ),
    ExampleItem(
        screen      = ExampleScreen.Permission,
        icon        = Icons.Outlined.Lock,
        tag         = "customDeniedDialog · customSettingsDialog",
        title       = "Custom Permission",
        description = "Replace the default permission dialogs with fully custom UI.",
        bulletPoints = listOf(
            "Custom dialog when permission is denied",
            "Custom dialog when permission is permanently blocked",
            "Each dialog manages its own visibility via Dialog()"
        ),
        warning = "Uninstall the app first (or revoke camera permission in Settings) to trigger the permission flow."
    ),
    ExampleItem(
        screen      = ExampleScreen.Confirmation,
        icon        = Icons.Outlined.PhotoLibrary,
        tag         = "customConfirmationView · skipConfirmation",
        title       = "Custom Confirmation",
        description = "Override the post-capture confirmation screen with your own composable.",
        bulletPoints = listOf(
            "Custom preview UI with file size and resolution chips",
            "Retake / Confirm actions",
            "Compare with skipConfirmation = true"
        ),
        warning = "Capture the photo first to trigger the confirmation flow."
    ),
    ExampleItem(
        screen      = ExampleScreen.Crop,
        icon        = Icons.Outlined.Crop,
        tag         = "CropConfig · enableCrop",
        title       = "Crop",
        description = "Enable the built-in crop editor after selecting or capturing a photo.",
        bulletPoints = listOf(
            "CropConfig: enabled, aspectRatioLocked, circularCrop",
            "launchCamera(enableCrop = true)",
            "launchGallery(enableCrop = true)"
        )
    ),
    ExampleItem(
        screen      = ExampleScreen.UiConfig,
        icon        = Icons.Outlined.Tune,
        tag         = "UiConfig · CameraCallbacks · GalleryConfig",
        title       = "UI & Extra Config",
        description = "Fine-tune camera behavior, change size capture size button ,change switch icon," +
                "change flash icon,change color and background confirmation view ",
        bulletPoints = listOf(
            "UiConfig: custom TopAppBar / BottomAppBar colors",
            "CapturePhotoPreference, CompressionLevel, MimeType",
            "CameraCallbacks: onPhotoTaken, onError",
            "GalleryConfig: allowMultiple, selectionLimit"
        ),
        warning = "Capture the photo first to trigger the confirmation flow."
    ),
    ExampleItem(
        screen      = ExampleScreen.GalleryMimeType,
        icon        = Icons.Outlined.Settings,
        tag         = "GalleryConfig · mimeTypes",
        title = "Gallery MimeType",
        description = "Filter image by type in the gallery.",
        bulletPoints = listOf(
            "GalleryConfig: allowMultiple, mimeTypes, mimeTypeMismatchMessage"
        )
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewApiRememberScreen(innerPadding: PaddingValues, onBack: () -> Unit) {

    var currentScreen by remember { mutableStateOf<ExampleScreen?>(null) }

    if (currentScreen != null) {
        when (currentScreen) {
            ExampleScreen.Basic        -> BasicUsageRememberScreen(onBack = { currentScreen = null })
            ExampleScreen.Permission   -> CustomPermissionRememberScreen(onBack = { currentScreen = null })
            ExampleScreen.Confirmation -> CustomConfirmationRememberScreen(innerPadding, onBack = { currentScreen = null })
            ExampleScreen.Crop         -> CropUsageRememberScreen(onBack = { currentScreen = null })
            ExampleScreen.UiConfig     -> CameraAndConfirmationDialogUiConfigRememberScreen(onBack = { currentScreen = null })
            ExampleScreen.GalleryMimeType -> MimeTypeFilterRememberScreen(onBack = { currentScreen = null })
            null                       -> Unit
        }
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("New API") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back to home"
                        )
                    }
                }
            )
        }
    ) { scaffoldPadding ->
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(scaffoldPadding),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item(span = { GridItemSpan(2) }) {
            Column(modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)) {
                Text(
                    text = "NEW API",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 0.08.em,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "rememberImagePickerKMP()",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Pick an example to explore each feature of the library.",
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider(
                    thickness = 0.5.dp,
                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
                )
            }
        }

        items(examples.filter { item ->
            // Confirmation and UiConfig are Android-only features
            if (item.screen == ExampleScreen.Confirmation || item.screen == ExampleScreen.UiConfig) {
                isAndroid
            } else {
                true
            }
        }) { item ->
            ExampleCard(item = item, onClick = { currentScreen = item.screen })
        }
    }
    }
}

@Composable
private fun ExampleCard(item: ExampleItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f)
        ),
        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.18f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.10f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = item.title,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = item.description,
                fontSize = 12.sp,
                lineHeight = 17.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(10.dp))
            Surface(
                shape = RoundedCornerShape(6.dp),
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.08f)
            ) {
                Text(
                    text = item.tag,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 0.02.em,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(horizontal = 7.dp, vertical = 4.dp),
                    maxLines = 2,
                    lineHeight = 13.sp
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            item.bulletPoints.forEach { point ->
                Row(
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier.padding(bottom = 3.dp)
                ) {
                    Text(
                        text = "·",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(end = 5.dp, top = 1.dp)
                    )
                    Text(
                        text = point,
                        fontSize = 11.sp,
                        lineHeight = 15.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            if (item.warning != null) {
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFFFF3CD))
                        .padding(8.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = null,
                        tint = Color(0xFF856404),
                        modifier = Modifier.size(13.dp).padding(top = 1.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = item.warning,
                        fontSize = 10.sp,
                        lineHeight = 14.sp,
                        color = Color(0xFF856404)
                    )
                }
            }
        }
    }
}
