package com.sumaya.hw_week06_day05.ui


import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.sumaya.hw_week06_day05.R

const val MOVIE_ID = "MOVIE_ID"


class WebView : AppCompatActivity() {
    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_view)

        webView = findViewById(R.id.wvId)
        progressBar = findViewById(R.id.progressBar)
        val movieId: Int = intent.getIntExtra(MOVIE_ID, 0)

        if (movieId != 0) {
            webView.settings.javaScriptEnabled = true
            webView.webViewClient = WebViewClient()
            webView.loadUrl("https://www.themoviedb.org/movie/${movieId}")

            webView.webChromeClient = object : WebChromeClient() {
                override fun onReceivedTitle(view: WebView?, title: String?) {
                    (view!!.context as AppCompatActivity).supportActionBar?.subtitle = title
                }

                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    if (newProgress == 100) {
                        progressBar.visibility = View.GONE
                    } else {
                        progressBar.visibility = View.VISIBLE
                        progressBar.progress = newProgress
                    }

                }

            }
        }
    }
}