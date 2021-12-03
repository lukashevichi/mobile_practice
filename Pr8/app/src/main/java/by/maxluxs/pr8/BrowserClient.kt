package by.maxluxs.pr8

import android.webkit.WebView

import android.webkit.WebViewClient

class BrowserClient : WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        view.loadUrl(url)
        return true
    }

}