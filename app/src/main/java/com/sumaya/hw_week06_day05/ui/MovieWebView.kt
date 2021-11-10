package com.sumaya.hw_week06_day05.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import com.sumaya.hw_week06_day05.R



class MovieWebView : AppCompatActivity(){
    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_web_view)

        webView = findViewById(R.id.wvId)
        progressBar = findViewById(R.id.progressBar)
        progressBar.max = 100

        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true

        val movieId = intent.getStringExtra("show")!!
        webView.loadUrl("https://www.themoviedb.org/movie/$movieId")


        webView.webChromeClient = object : WebChromeClient(){
            override fun onReceivedTitle(view: WebView?, title: String?) {
                (this@MovieWebView as AppCompatActivity).supportActionBar?.subtitle = title
            }

            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                if(newProgress == 100){
                    progressBar.visibility = View.GONE
                }else{
                    progressBar.visibility = View.VISIBLE
                    progressBar.progress = newProgress
                }
            }
        }

    }
}