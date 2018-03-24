package papered.startupweekend.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_profile_input.*
import papered.startupweekend.R

class ProfileInputActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_input)
        profile_submit.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}
