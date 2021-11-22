package com.example.playo.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.playo.R

class MainActivity : AppCompatActivity() {

	private val handler = Handler()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		//hide actionbar
		var actionBar: ActionBar? = supportActionBar
		actionBar?.hide()

		//start activity after 3 sec
		handler.postDelayed(object : Runnable {
			override fun run() {
				val intent = Intent(this@MainActivity, HomePage::class.java)
				startActivity(intent)
				overridePendingTransition(0, R.anim.fade)
				handler.removeCallbacks(this)
			}
		}, 3000) // duration of handler
	}
}