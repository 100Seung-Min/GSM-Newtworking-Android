package com.gsm.networking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import com.google.accompanist.web.AccompanistWebChromeClient
import com.google.accompanist.web.WebView
import com.gsm.networking.ui.theme.GSMNetworkingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainViewModel = MainViewModel()
        val webViewClient = CustomWebViewClient(this)
        val webChromeClient = AccompanistWebChromeClient()
        setContent {
            val webViewState = mainViewModel.webViewState
            val webViewNavigator = mainViewModel.webViewNavigator

            GSMNetworkingTheme {
                WebView(
                    state = webViewState,
                    client = webViewClient,
                    chromeClient = webChromeClient,
                    navigator = webViewNavigator,
                    onCreated = {
                        with(it) {
                            settings.run {
                                javaScriptEnabled = true
                                domStorageEnabled = false
                                javaScriptCanOpenWindowsAutomatically = false
                            }
                        }
                    }
                )
                BackHandler {
                    if (webViewNavigator.canGoBack) {
                        webViewNavigator.navigateBack()
                    } else {
                        finish()
                    }
                }
            }
        }
    }
}