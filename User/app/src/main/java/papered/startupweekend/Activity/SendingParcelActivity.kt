package papered.startupweekend.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_sending_parcel.*
import papered.startupweekend.R

class SendingParcelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sending_parcel)

        var country : String?
        sending_spinner_country.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                country = sending_spinner_country.getItemAtPosition(p2).toString()
                Toast.makeText(baseContext,"${sending_spinner_country.getItemAtPosition(p2)}",Toast.LENGTH_SHORT).show()
            }

        }
        sending_image_small.setOnClickListener {
            sending_text_small.text = (sending_text_small.text.toString().toInt() + 1).toString()
        }
        sending_image_middle.setOnClickListener {
            sending_text_middle.text = (sending_text_middle.text.toString().toInt() + 1).toString()
        }
        sending_image_large.setOnClickListener {
            sending_text_large.text = (sending_text_large.text.toString().toInt() + 1).toString()
        }

        sending_seekBar_weight.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                sending_text_kg.text = "${p1}Kg"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })
        sending_button_nextStep.setOnClickListener {
            startActivity(Intent(this, ShowPriceActivity::class.java))
        }
    }
}
