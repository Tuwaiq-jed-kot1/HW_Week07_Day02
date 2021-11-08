package com.sumaya.hw_week06_day05.data.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.sumaya.hw_week06_day05.R


class WebViewFragment : Fragment() {

    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_web_view, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView=view.findViewById(R.id.webView)
        progressBar=view.findViewById(R.id.progressBar)

        // Gets the data from the passed bundle
        val bundle = arguments
        val url = bundle!!.getString("urlString")

        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("https://www.themoviedb.org/movie/$url")
        webView.webChromeClient = object : WebChromeClient() {
            override fun onReceivedTitle(webView: WebView, title: String?) {
                (activity as AppCompatActivity).supportActionBar?.subtitle = title
            }
            override fun onProgressChanged(webView: WebView, newProgress: Int) {
                if (newProgress == 100) {
                    progressBar.visibility = View.GONE
                } else { progressBar.visibility = View.VISIBLE
                    progressBar.progress = newProgress }
            }
        }

    }




}