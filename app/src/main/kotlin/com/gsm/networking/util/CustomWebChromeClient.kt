package com.gsm.networking.util

import android.net.Uri
import android.webkit.ValueCallback
import android.webkit.WebView
import com.google.accompanist.web.AccompanistWebChromeClient

interface WebChromeEventListener {
    fun goToGallery(filePathCallback: ValueCallback<Array<Uri>>?)
}

class CustomWebChromeClient(
    private val webChromeEventListener: WebChromeEventListener,
) : AccompanistWebChromeClient() {
    override fun onShowFileChooser(
        webView: WebView?,
        filePathCallback: ValueCallback<Array<Uri>>?,
        fileChooserParams: FileChooserParams?,
    ): Boolean {
        webChromeEventListener.goToGallery(filePathCallback)
        return true
    }
}