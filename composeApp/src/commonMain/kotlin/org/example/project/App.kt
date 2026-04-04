package org.example.project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.ImagePickerRememberAPI.NewApiRememberScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

private enum class AppScreen { Home, NewApi, Legacy }

@Composable
@Preview
fun App() {
    var screen by remember { mutableStateOf(AppScreen.Home) }

    MaterialTheme {
        when (screen) {
            AppScreen.Home -> HomeScreen(
                onNewApi = { screen = AppScreen.NewApi },
                onLegacy = { screen = AppScreen.Legacy }
            )
            AppScreen.NewApi -> Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                NewApiRememberScreen(innerPadding = innerPadding, onBack = { screen = AppScreen.Home })
            }
            AppScreen.Legacy -> CameraScreen(onBack = { screen = AppScreen.Home })
        }
    }
}

@Composable
private fun HomeScreen(onNewApi: () -> Unit, onLegacy: () -> Unit) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "ImagePickerKMP",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Choose an API to explore",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(48.dp))
            Button(
                onClick = onNewApi,
                modifier = Modifier.fillMaxWidth().height(52.dp),
                shape = RoundedCornerShape(14.dp)
            ) {
                Text("New API  →  rememberImagePickerKMP()", fontSize = 13.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedButton(
                onClick = onLegacy,
                modifier = Modifier.fillMaxWidth().height(52.dp),
                shape = RoundedCornerShape(14.dp)
            ) {
                Text("Legacy API  →  CameraScreen", fontSize = 13.sp)
            }
        }
    }
}