package com.sumaya.hw_week06_day05.data.network

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.sumaya.hw_week06_day05.R


class MoviesWebView : AppCompatActivity() {
    private lateinit var webView : WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_web_view)
        webView=findViewById(R.id.mId)

        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
        val movieId=intent.getStringExtra("mKey")
        webView.loadUrl("https://www.themoviedb.org/movie/$movieId")
        webView.webChromeClient = object : WebChromeClient(){
            override fun onReceivedTitle(view: WebView?, title: String?) {
                (this@MoviesWebView as AppCompatActivity).supportActionBar?.subtitle=title
            }

        }
    }
}
