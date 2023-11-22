package com.gsm.networking.util

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.web.WebViewNavigator

@Composable
fun CheckBackHandler(
    webViewNavigator: WebViewNavigator? = null,
    finishAction: () -> Unit,
) {
    var waitTime = 0L
    val context = LocalContext.current
    BackHandler {
        if (webViewNavigator != null && webViewNavigator.canGoBack) {
            webViewNavigator.navigateBack()
        } else {
            if (System.currentTimeMillis() - waitTime >= 1500) {
                waitTime = System.currentTimeMillis()
                Toast.makeText(
                    context,
                    "뒤로가기 버튼을 한번 더 누르면 종료됩니다.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                finishAction()
            }
        }
    }
}