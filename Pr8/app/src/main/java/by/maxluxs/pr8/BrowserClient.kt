package by.maxluxs.pr8

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible

class BrowserClient : WebViewClient() {

    var progress: ProgressBar? = null
    var context: Context? = null

    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        view.loadUrl(url)
        return true
    }

    override fun onPageFinished(view: WebView, url: String) {
        if (progress?.isVisible == true) {
            progress?.isVisible = false
        }
    }

    override fun onReceivedError(
        view: WebView,
        errorCode: Int,
        description: String,
        failingUrl: String
    ) {
        Toast.makeText(context, "Error:$description", Toast.LENGTH_SHORT)
            .show()
    }

}