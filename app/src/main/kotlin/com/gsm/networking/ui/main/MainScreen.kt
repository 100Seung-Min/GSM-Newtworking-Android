package com.gsm.networking.ui.main

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.webkit.ValueCallback
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.web.WebView
import com.gsm.networking.ui.theme.GSMNetworkingTheme
import com.gsm.networking.util.CheckBackHandler
import com.gsm.networking.util.CustomWebChromeClient
import com.gsm.networking.util.CustomWebViewClient
import com.gsm.networking.util.WebChromeEventListener
import com.gsm.networking.viewmodel.MainViewModel

@Composable
fun MainScreen(
    mainViewModel: MainViewModel,
    finishAction: () -> Unit,
) {
    val context = LocalContext.current
    var callback: ValueCallback<Array<Uri>>? = null
    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val uri = result.data?.data
                callback?.onReceiveValue(if (uri != null) arrayOf(uri) else null)
            } else {
                callback?.onReceiveValue(null)
            }
        }
    val webViewNavigator = mainViewModel.webViewNavigator
    val webViewState = mainViewModel.webViewState
    val webViewClient = CustomWebViewClient(context, webViewNavigator)
    val webChromeClient =
        CustomWebChromeClient(webChromeEventListener = object : WebChromeEventListener {
            override fun goToGallery(filePathCallback: ValueCallback<Array<Uri>>?) {
                callback = filePathCallback
                galleryLauncher.launch(
                    Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    ).apply {
                        type = "image/*"
                        action = Intent.ACTION_PICK
                        putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false)
                    })
            }
        })

    GSMNetworkingTheme {
        CheckBackHandler(webViewNavigator = webViewNavigator, finishAction = finishAction)
        WebView(
            state = webViewState,
            client = webViewClient,
            chromeClient = webChromeClient,
            navigator = webViewNavigator,
            captureBackPresses = false,
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