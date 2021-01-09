package org.wit.hillfort.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import org.wit.hillfort.R
import org.wit.hillfort.views.login.LoginView


class SplashScreenActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()

        Handler().postDelayed({
            val intent = Intent(this@SplashScreenActivity, LoginView::class.java)
            startActivity(intent)
            finish()
        }, 2000)

        }
    }

/*REFERENCES:
https://www.youtube.com/watch?v=Q0gRqbtFLcw
https://www.youtube.com/watch?v=S1r6CPDuBEE*/
