package org.example.project.ocrUsage

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable


@Composable
fun OCRUsageScreen(
    innerPadding: PaddingValues,
    isOCRActive: Boolean, 
    onOCRActiveChange: (Boolean) -> Unit
){
    /*var resultOCR by remember { mutableStateOf<OCRResult?>(null) }
    Column(
        modifier = Modifier.padding(innerPadding)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            if (isOCRActive) {
                ImagePickerLauncherOCR(
                    config = ImagePickerOCRConfig(
                        scanMode = ScanMode.Cloud(
                            provider = CloudOCRProvider.Gemini("YOUR_GEMINI_API_KEY")
                        ),

                        onOCRCompleted = { result ->
                            resultOCR = result
                            onOCRActiveChange(false)
                        },
                        onError = {
                            onOCRActiveChange(false)
                            println("Error en OCR: $it")
                        },
                        onCancel = {
                            onOCRActiveChange(false)
                        },
                        directCameraLaunch = false,
                        allowedMimeTypes =listOf(MimeType.APPLICATION_PDF, MimeType.IMAGE_ALL),
                    )
                )*/

              /*  // Custom OCR service with simple authentication:
                ImagePickerLauncherOCR(
                    config = ImagePickerOCRConfig(
                        scanMode = ScanMode.Cloud(
                            provider = CloudOCRProvider.Custom(
                                name = "MyCompany OCR",
                                baseUrl = "https://api.mycompany.com/ocr/analyze",
                                apiKey = "abc123def456",
                                requestFormat = RequestFormat.MULTIPART_FORM
                            )
                        ),

                        onOCRCompleted = { result ->
                            resultOCR = result
                            onOCRActiveChange(false)
                        },
                        onError = {
                            onOCRActiveChange(false)
                            println("Error en OCR: $it")
                        },
                        onCancel = {
                            onOCRActiveChange(false)
                        },
                        directCameraLaunch = false,
                        allowedMimeTypes =listOf(MimeType.APPLICATION_PDF, MimeType.IMAGE_ALL),
                    )
                )*/

               /* //Service without authentication (local or public):
                ImagePickerLauncherOCR(
                    config = ImagePickerOCRConfig(
                        scanMode = ScanMode.Cloud(
                            provider = CloudOCRProvider.Custom(
                                name = "Local OCR Server",
                                baseUrl = "http://localhost:8080/api/ocr",
                                apiKey = null, // no API key
                                requestFormat = RequestFormat.JSON_URL
                            )
                        ),

                        onOCRCompleted = { result ->
                            resultOCR = result
                            onOCRActiveChange(false)
                        },
                        onError = {
                            onOCRActiveChange(false)
                            println("Error en OCR: $it")
                        },
                        onCancel = {
                            onOCRActiveChange(false)
                        },
                        directCameraLaunch = false,
                        allowedMimeTypes =listOf(MimeType.APPLICATION_PDF, MimeType.IMAGE_ALL),
                    )
                )*/

                // Service with custom headers:
                /*ImagePickerLauncherOCR(
                    config = ImagePickerOCRConfig(
                        scanMode = ScanMode.Cloud(
                            provider = CloudOCRProvider.Custom(
                                name = "Enterprise OCR Service",
                                baseUrl = "https://enterprise-ocr.internal.com/v2/extract",
                                headers = mapOf(
                                    "X-API-Version" to "2.1",
                                    "X-Client-ID" to "mobile-app",
                                    "Authorization" to "Bearer $token",
                                    "User-Agent" to "ImagePickerKMP/1.0"
                                )
                        )
                        ),

                        onOCRCompleted = { result ->
                            resultOCR = result
                            onOCRActiveChange(false)
                        },
                        onError = {
                            onOCRActiveChange(false)
                            println("Error en OCR: $it")
                        },
                        onCancel = {
                            onOCRActiveChange(false)
                        },
                        directCameraLaunch = false,
                        allowedMimeTypes =listOf(MimeType.APPLICATION_PDF, MimeType.IMAGE_ALL),
                    )
                )*/


                /*//Service using JSON request format:
                ImagePickerLauncherOCR(
                    config = ImagePickerOCRConfig(
                        scanMode = ScanMode.Cloud(
                            provider = CloudOCRProvider.Custom(
                                name = "JSON OCR API",
                                baseUrl = "https://api.json-ocr.com/v1/process",
                                apiKey = "json-api-key-123",
                                headers = mapOf(
                                    "Content-Type" to "application/json"
                                ),
                                requestFormat = RequestFormat.JSON_URL
                        )
                        ),

                        onOCRCompleted = { result ->
                            resultOCR = result
                            onOCRActiveChange(false)
                        },
                        onError = {
                            onOCRActiveChange(false)
                            println("Error en OCR: $it")
                        },
                        onCancel = {
                            onOCRActiveChange(false)
                        },
                        directCameraLaunch = false,
                        allowedMimeTypes =listOf(MimeType.APPLICATION_PDF, MimeType.IMAGE_ALL),
                    )
                )*/
            }
            /*resultOCR?.let { ocrResult ->
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        shape = RoundedCornerShape(12.dp),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Resultado OCR",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 12.dp)
                            )

                            ScrollableText(
                                text = ocrResult.getMetadataAsJson(),
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            OutlinedButton(
                                onClick = { resultOCR = null },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Limpiar Resultado")
                            }
                        }
                    }
                }
            }

        }
    }
}
@Composable
fun ScrollableText(
    text: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.height(250.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            contentPadding = PaddingValues(4.dp)
        ) {
            item {
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 12.sp,
                        lineHeight = 16.sp
                    ),
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}
*/