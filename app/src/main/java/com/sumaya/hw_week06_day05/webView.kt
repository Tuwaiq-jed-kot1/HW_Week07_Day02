package com.sumaya.hw_week06_day05

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar

class webView : AppCompatActivity() {
    private lateinit var webView: WebView
    private lateinit var progress: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        progress = findViewById(R.id.progressBar)
        progress.max = 100   // 78 progress bar download y3ny
        webView = findViewById(R.id.wvId)
        val movieId: Int = intent.getIntExtra("M", 0)
        webView.webViewClient = WebViewClient() // fo web that has S or Not but not is importint
        webView.settings.javaScriptEnabled = true // 3shan b36' al moa83 mn no3  java
        webView.loadUrl("https://www.themoviedb.org/movie/$movieId")
        webView.webChromeClient = object : WebChromeClient() {
            override fun onReceivedTitle(view: WebView?, title: String?) {
                (this@webView as AppCompatActivity).supportActionBar?.subtitle = title
            }

            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                if (newProgress == 100) {
                    progress.visibility = View.GONE
                } else {
                    progress.visibility = View.VISIBLE
                    progress.progress = newProgress
                }
            }
        }
    }
}