package com.gsm.networking.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.web.AccompanistWebChromeClient
import com.google.accompanist.web.WebView
import com.gsm.networking.ui.theme.GSMNetworkingTheme
import com.gsm.networking.util.CheckBackHandler
import com.gsm.networking.util.CustomWebViewClient
import com.gsm.networking.viewmodel.MainViewModel

@Composable
fun MainScreen(
    mainViewModel: MainViewModel,
    finishAction: () -> Unit,
) {
    val context = LocalContext.current
    val webViewNavigator = mainViewModel.webViewNavigator
    val webViewState = mainViewModel.webViewState
    val webViewClient = CustomWebViewClient(context, webViewNavigator)
    val webChromeClient = AccompanistWebChromeClient()
    GSMNetworkingTheme {
        CheckBackHandler(finishAction = finishAction)
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
                        userAgentString = "Chrome/56.0.0.0 Mobile"
                    }
                }
            }
        )
    }
}