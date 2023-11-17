package com.gsm.networking.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.webkit.CookieManager
import android.webkit.WebResourceRequest
import android.webkit.WebView
import com.google.accompanist.web.AccompanistWebViewClient
import com.google.accompanist.web.WebViewNavigator

class CustomWebViewClient(
    private val context: Context,
    private val webViewNavigator: WebViewNavigator,
) : AccompanistWebViewClient() {
    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        CookieManager.getInstance().flush()
    }

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        val url = request?.url?.toString() ?: ""
        if (url.contains("accounts.google.com") && url.contains("gsm-networking").not()) {
            webViewNavigator.reload()
            return true
        }
        val intent = if (url.startsWith("mailto:")) {
            Intent(Intent.ACTION_SENDTO).apply {
                type = "plain/text"
                data = Uri.parse(url)
            }
        } else if (url.contains("gsm-networking") && url.contains("notion").not()) {
            return super.shouldOverrideUrlLoading(view, request)
        } else {
            Intent(Intent.ACTION_VIEW, Uri.parse(url))
        }
        intent.let { context.startActivity(it) }
        return true
    }
}