package io.github.rikuyu.contactlensreminder.presentation.screens.app_setting.inquiry.component

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import io.github.rikuyu.contactlensreminder.R
import io.github.rikuyu.contactlensreminder.presentation.theme.CleanBlue
import io.github.rikuyu.contactlensreminder.presentation.theme.SkyBlue
import io.github.rikuyu.contactlensreminder.presentation.theme.WebViewBackground
import io.github.rikuyu.contactlensreminder.presentation.util.Routes
import io.github.rikuyu.contactlensreminder.presentation.util.checkNetworkConnection

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun ContactUsScreen(navController: NavController) {

    val formId = "1FAIpQLScyhLuzmtG6fcmLzJ0lEC4pXHU5KwrCW7Le_K1_P41pnfP10g"

    val isNetworkConnected = checkNetworkConnection(LocalContext.current)

    val visibility = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.inquiry))
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(Routes.APP_SETTING) }) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                },
                contentColor = Color.White,
                backgroundColor = CleanBlue
            )
        }
    ) {
        if (isNetworkConnected) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(WebViewBackground)
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    AndroidView(
                        factory = {
                            WebView(it).apply {
                                webViewClient = WebViewClient()
                                settings.javaScriptEnabled = true
                            }
                        },
                        update = { webView ->
                            webView.webViewClient = object : WebViewClient() {
                                override fun onPageStarted(
                                    view: WebView,
                                    url: String,
                                    favicon: Bitmap?
                                ) {
                                    visibility.value = true
                                }
                            }
                            webView.loadUrl("https://docs.google.com/forms/d/e/$formId/viewform")
                        }
                    )
                    if (!visibility.value) {
                        CircularProgressIndicator(
                            color = SkyBlue,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_wifi_off),
                    modifier = Modifier.size(60.dp),
                    tint = SkyBlue,
                    contentDescription = null
                )
                Text(
                    text = stringResource(id = R.string.network_error_message_1),
                    color = CleanBlue,
                    fontSize = 18.sp
                )
                Text(
                    text = stringResource(id = R.string.network_error_message_2),
                    color = CleanBlue,
                    fontSize = 18.sp
                )
            }
        }
    }
}