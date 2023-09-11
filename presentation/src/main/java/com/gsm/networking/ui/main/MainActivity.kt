package com.gsm.networking.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import com.google.accompanist.web.AccompanistWebChromeClient
import com.google.accompanist.web.WebView
import com.gsm.networking.util.CustomWebViewClient
import com.gsm.networking.viewmodel.MainViewModel
import com.gsm.networking.ui.theme.GSMNetworkingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainViewModel = MainViewModel()
        val webViewClient = CustomWebViewClient(this)
        val webChromeClient = AccompanistWebChromeClient()
        var waitTime = 0L
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
                        if(System.currentTimeMillis() - waitTime >=1500 ) {
                            waitTime = System.currentTimeMillis()
                            Toast.makeText(this,"뒤로가기 버튼을 한번 더 누르면 종료됩니다.",Toast.LENGTH_SHORT).show()
                        } else {
                            finish()
                        }
                    }
                }
            }
        }
    }
}