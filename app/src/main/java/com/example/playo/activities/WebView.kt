package com.example.playo.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.playo.R
import kotlinx.android.synthetic.main.activity_web_view.*

class WebView : AppCompatActivity() {

    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        // hiding actionBar
        var actionBar: ActionBar? = supportActionBar
        actionBar?.hide()

        val bundle = intent.extras
        val url = bundle!!.getString("url")

        // this will load the url of the website
        if (url != null) {
            webView.visibility = View.VISIBLE
            webView.webViewClient = WebViewClient()
            webView.loadUrl(url)
            // this will enable the javascript settings
            webView.settings.javaScriptEnabled = true

            // if you want to enable zoom feature
            webView.settings.setSupportZoom(true)
        }else{
            webView.visibility = View.INVISIBLE
            Toast.makeText(this@WebView, "Can't load the URL", Toast.LENGTH_LONG).show()

            handler.postDelayed(object : Runnable {
                override fun run() {
                    val intent = Intent(this@WebView, HomePage::class.java)
                    startActivity(intent)
                    overridePendingTransition(0, R.anim.fade)
                    handler.removeCallbacks(this)
                }
            }, 3000) // duration of handler
        }


    }
}