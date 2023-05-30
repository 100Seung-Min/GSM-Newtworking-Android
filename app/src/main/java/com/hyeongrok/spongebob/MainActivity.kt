package com.hyeongrok.spongebob

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.web.AccompanistWebChromeClient
import com.google.accompanist.web.AccompanistWebViewClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState
import com.hyeongrok.spongebob.ui.theme.SpongeBobTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainViewModel = MainViewModel()
        val webViewClient = AccompanistWebViewClient()
        val webChromeClient = AccompanistWebChromeClient()
        setContent {
            val webViewState = mainViewModel.webViewState
            val webViewNavigator = mainViewModel.webViewNavigator

            SpongeBobTheme {
                WebView(
                    state = webViewState,
                    client = webViewClient,
                    chromeClient = webChromeClient,
                    navigator = webViewNavigator,
                    onCreated = {
                        with(it) {
                            settings.run {
                                javaScriptEnabled = true
                                domStorageEnabled = true
                                javaScriptCanOpenWindowsAutomatically = false
                            }
                        }
                    }
                )
            }
        }
    }
}