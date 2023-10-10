package com.gsm.networking.util

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.webkit.WebResourceRequest
import android.webkit.WebView
import com.google.accompanist.web.AccompanistWebViewClient
import com.gsm.networking.BuildConfig

class CustomWebViewClient(private val context: Context) : AccompanistWebViewClient() {
    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
    }

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        val intent = if (request?.url.toString().startsWith("mailto:")) {
            Intent(Intent.ACTION_SENDTO).apply {
                type = "plain/text"
                data = request?.url
            }
        } else if (request?.url.toString()
                .let { it.contains("gsm-networking") && it.contains("notion").not() }
        ) {
            return super.shouldOverrideUrlLoading(view, request)
        } else {
            Intent(Intent.ACTION_VIEW, request?.url)
        }
        intent.let { context.startActivity(it) }
        return true
    }
}