package org.wit.hillfort.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.HandlerCompat.postDelayed
import org.wit.hillfort.R
import org.wit.hillfort.main.MainApp

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        Handler().postDelayed({
            val intent = Intent(this@SplashScreenActivity, HillfortListActivity::class.java)
            startActivity(intent)
            finish()
        }, 4000)

        }
    }

/*REFERENCES:
https://www.youtube.com/watch?v=Q0gRqbtFLcw
https://www.youtube.com/watch?v=S1r6CPDuBEE*/
