package org.example.project

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.viewinterop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.cValue
import platform.CoreGraphics.CGRect
import platform.Foundation.NSData
import platform.Foundation.NSURL
import platform.Foundation.base64EncodedStringWithOptions
import platform.Foundation.dataWithContentsOfURL
import platform.UIKit.UIColor
import platform.WebKit.WKWebView
import platform.WebKit.WKWebViewConfiguration

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun AnimatedGifImage(
    uri: String,
    contentDescription: String,
    modifier: Modifier,
    contentScale: ContentScale
) {
    val html = buildGifHtml(uri)

    UIKitView(
        factory = {
            val config = WKWebViewConfiguration()
            val webView = WKWebView(frame = cValue<CGRect>(), configuration = config)
            webView.scrollView.scrollEnabled = false
            webView.scrollView.bounces = false
            webView.backgroundColor = UIColor.clearColor
            webView.opaque = false
            webView.loadHTMLString(html, baseURL = null)
            webView
        },
        modifier = modifier,
        update = { webView ->
            webView.loadHTMLString(html, baseURL = null)
        }
    )
}

private fun buildGifHtml(uri: String): String {
    val filePath = when {
        uri.startsWith("file://") -> uri.removePrefix("file://")
        uri.startsWith("file:") -> uri.removePrefix("file:")
        else -> uri
    }
    val nsUrl = NSURL.fileURLWithPath(filePath)
    val data: NSData? = NSData.dataWithContentsOfURL(nsUrl)
    val base64 = data?.base64EncodedStringWithOptions(0u) ?: ""

    return """
        <!DOCTYPE html>
        <html>
        <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
          * { margin: 0; padding: 0; box-sizing: border-box; }
          html, body { width: 100%; height: 100%; background: transparent; }
          img { width: 100%; height: 100%; object-fit: contain; display: block; }
        </style>
        </head>
        <body>
          <img src="data:image/gif;base64,$base64" />
        </body>
        </html>
    """.trimIndent()
}
