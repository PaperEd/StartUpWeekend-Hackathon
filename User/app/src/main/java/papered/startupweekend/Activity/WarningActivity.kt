package papered.startupweekend.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_warning.*
import papered.startupweekend.R

class WarningActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_warning)

        warning_button_next.setOnClickListener {
            startActivity(Intent(this,SendingParcelActivity::class.java))
        }
    }
}
