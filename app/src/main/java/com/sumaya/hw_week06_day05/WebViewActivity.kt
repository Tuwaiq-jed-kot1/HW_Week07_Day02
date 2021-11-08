package com.sumaya.hw_week06_day05

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
class WebViewActivity : AppCompatActivity() {
    private lateinit var webView : WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.webview_activity)
        webView=findViewById(R.id.wvId)

        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
//getExtra
        val movieId=intent.getStringExtra("webKey")
        webView.loadUrl("https://www.themoviedb.org/movie/$movieId")
        webView.webChromeClient = object : WebChromeClient(){
            override fun onReceivedTitle(view: WebView?, title: String?) {
                (this@WebViewActivity as AppCompatActivity).supportActionBar?.subtitle=title
            }

    }
    }
}