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

        sending_image_small.setOnClickListener {
            sending_text_small.text =  (sending_text_small.text.toString().toInt() + 1).toString()
        }
        sending_image_middle.setOnClickListener {
            sending_text_middle.text =  (sending_text_middle.text.toString().toInt() + 1).toString()
        }
        sending_image_large.setOnClickListener {
            sending_text_large.text =  (sending_text_large.text.toString().toInt() + 1).toString()
        }

        sending_button_nextStep.setOnClickListener {
            startActivity(Intent(this,ShowPriceActivity::class.java))
        }
    }
}
