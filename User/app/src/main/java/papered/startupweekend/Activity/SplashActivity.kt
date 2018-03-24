package papered.startupweekend.Activity

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import papered.startupweekend.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val sp = getSharedPreferences("userKey", Activity.MODE_PRIVATE)
        val key: String? = sp.getString("key", "not_key")
        if (key != "not_key") {
            startActivity(Intent(this,MainActivity::class.java))
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        finish()
    }
}
