package com.example.contactlensreminder.presentation.screens.app_setting.inquiry.component

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun ContactUsScreen() {

    val url = "https://www.yuuuki-blog.com/"

    AndroidView(
        factory = { WebView(it) },
        update = { webView ->
            webView.webViewClient = WebViewClient()
            webView.loadUrl(url)
        }
    )
}