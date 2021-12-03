package by.maxluxs.pr8

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.Menu
import android.view.View
import android.webkit.WebView
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import java.lang.Exception
import java.lang.reflect.Method


class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var searchView: SearchView

    private var url: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        webView = findViewById(R.id.webView)
        webView.webViewClient = BrowserClient()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        return when (id) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        super.onPrepareOptionsMenu(menu)
        val searchViewMenuItem = menu.findItem(R.id.action_search)
        searchView = searchViewMenuItem.actionView as SearchView
        val v: ImageView =
            searchView.findViewById(R.id.search_button) as ImageView
        if (url.isNotEmpty()) {
            searchView.isIconified = false
            searchView.setQuery(url, false)
        }
        searchView.queryHint = "Enter address"
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            @SuppressLint("SetJavaScriptEnabled")
            override fun onQueryTextSubmit(query: String?): Boolean {
                url = searchView.query.toString()
                webView.settings.loadsImagesAutomatically = true
                webView.settings.javaScriptEnabled = true
                webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
                webView.progress
                try {
                    webView.loadUrl(url)
                } catch (e: Exception) {
                    webView.loadUrl("https://www.google.com/search?q=$url&oq=$url")
                    e.printStackTrace()
                }
                return true
            }

            @RequiresApi(Build.VERSION_CODES.O)
            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty())
                    clearSearch()
                else {
                    webView.findAllAsync(newText)
                    try {
                        val m: Method =
                            WebView::class.java.getMethod("setFindIsUp", java.lang.Boolean.TYPE)
                        m.invoke(webView, true)
                    } catch (ignored: Throwable) {
                    }
                }
                return false
            }

        })
        return true
    }

    private fun clearSearch() {
        searchView.setQuery(url, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

}