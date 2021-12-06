package by.maxluxs.pr8

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
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
import java.lang.reflect.Method
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.core.view.isVisible


/**
 * Наберите и выполните программу. Поработайте с различными URLадресами.
 * Используя приведениные сведения добавьте возможность
 * работать с историей посещения сайтов.
 * */

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var searchView: SearchView
    private lateinit var progressBar: ProgressBar

    companion object {
        const val APP_PREF = "P8_PREF"
    }

    private val viewModel: MainViewModel by viewModels()

    private val url get() = viewModel.url.value

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayShowTitleEnabled(true)
        initViews()
        observeUrl()
    }

    private fun initViews() {
        progressBar = findViewById(R.id.progress)
        initWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        webView = findViewById(R.id.webView)
        webView.webViewClient = BrowserClient().apply {
            progress = progressBar
            context = this@MainActivity
        }
        webView.settings.loadsImagesAutomatically = true
        webView.settings.javaScriptEnabled = true
        webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        webView.loadUrl("https://google.com/")
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun observeUrl() = viewModel.url.observe(this) { url ->
        url?.let {
            progressBar.isVisible = true
            webView.loadUrl(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        return when (id) {
            R.id.action_settings -> true
            R.id.action_reload -> {
                webView.reload()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        super.onPrepareOptionsMenu(menu)
        val searchViewMenuItem = menu.findItem(R.id.action_search)
        searchView = searchViewMenuItem.actionView as SearchView
        val v: ImageView =
            searchView.findViewById(R.id.search_button) as ImageView
        if (!url.isNullOrEmpty()) {
            searchView.isIconified = false
            searchView.setQuery(url, false)
        }
        v.setImageResource(R.drawable.search)
        searchView.isSubmitButtonEnabled = true
        searchView.queryHint = "Enter address"
        searchView.setIconifiedByDefault(false)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.setUrl(searchView.query.toString())
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

    override fun onBackPressed() {
        if (webView.canGoBack()) webView.goBack()
        else super.onBackPressed()
    }

    private fun clearSearch() {
        searchView.setQuery("", false)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

}