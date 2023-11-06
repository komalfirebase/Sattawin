package satta.win.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import satta.win.R
import satta.win.auth.LoginActivity
import satta.win.main.MainActivity

class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT = 5000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
      /*  val typeface = ResourcesCompat.getFont(this)*/
        val appname = findViewById<TextView>(R.id.appname)
      /*  appname.setTypeface(typeface)*/


        Handler().postDelayed({ // This method will be executed once the timer is over
            // Start your app main activity
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT.toLong())
    }
}