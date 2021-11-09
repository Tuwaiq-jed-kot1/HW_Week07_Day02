
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.sumaya.hw_week06_day05.R
import com.sumaya.hw_week06_day05.URL_KEY

class WebViewActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        webView = findViewById(R.id.movieWV)
        progressBar = findViewById(R.id.progressBar)
        progressBar.max = 100
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
        intent.getStringExtra(URL_KEY)?.let { webView.loadUrl(it) }
        webView.webChromeClient = object : WebChromeClient() {
            override fun onReceivedTitle(view: WebView?, title: String?) {
                (this@WebViewActivity as AppCompatActivity).supportActionBar?.subtitle = title
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