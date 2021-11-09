package com.sumaya.hw_week06_day05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar

class WebViewActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_view_activity)

        webView = findViewById(R.id.webView)
        progressBar = findViewById(R.id.progressBar)
        progressBar.max = 100
        val moviesID:String? = intent.getStringExtra("id")


        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("https://www.themoviedb.org/movie/" + moviesID)

        //UI design
        webView.webChromeClient = object : WebChromeClient(){
            override fun onReceivedTitle(view: WebView?, title: String?) {
                (this@WebViewActivity as AppCompatActivity).supportActionBar?.subtitle = title
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