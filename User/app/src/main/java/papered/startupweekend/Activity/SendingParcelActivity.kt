package papered.startupweekend.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_sending_parcel.*
import papered.startupweekend.R

class SendingParcelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sending_parcel)

        var startingPoint: String = "서울"
        var arrivalPoint = "서울"
        val dataBase = FirebaseFirestore.getInstance()
        val parcel = HashMap<String, Any>()
        sending_spinner_start.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                startingPoint = sending_spinner_start.getItemAtPosition(p2).toString()
                Toast.makeText(baseContext, "${sending_spinner_start.getItemAtPosition(p2)}", Toast.LENGTH_SHORT).show()
            }

        }
        sending_spinner_arrival.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                arrivalPoint = sending_spinner_start.getItemAtPosition(p2).toString()
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
            parcel.put("starting_point", startingPoint)
            parcel.put("arrival_point", arrivalPoint)
            parcel.put("small_item_count", sending_text_small.text.toString())
            parcel.put("middle_item_count", sending_text_middle.text.toString())
            parcel.put("large_item_count", sending_text_large.text.toString())
            parcel.put("item_weight", sending_text_kg.text.toString())
            parcel.put("arrival_detail_address", sending_editText_detailAdress.text.toString())
            parcel.put("approval", false)
            parcel.put("item_status", "not accepted")
            dataBase.collection("parcel")
                    .add(parcel)
                    .addOnSuccessListener {
                        Toast.makeText(baseContext, "화물이 등록되었습니다!", Toast.LENGTH_SHORT).show()
                    }
            startActivity(Intent(this, ShowPriceActivity::class.java))
            finish()
        }
    }
}
