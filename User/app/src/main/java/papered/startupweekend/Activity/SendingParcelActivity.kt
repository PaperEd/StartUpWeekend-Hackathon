package papered.startupweekend.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_sending_parcel.*
import papered.startupweekend.R

class SendingParcelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sending_parcel)
        sending_nextButton.setOnClickListener {
            startActivity(Intent(this,ShowPriceActivity::class.java))
        }
    }
}
