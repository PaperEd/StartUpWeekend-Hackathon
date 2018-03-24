package papered.startupweekend.Activity

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_receive.*
import kotlinx.android.synthetic.main.activity_sending_parcel.*
import papered.startupweekend.Model.AirportModel
import papered.startupweekend.R

class ReceiveActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receive)
        val dataBase = FirebaseFirestore.getInstance()
        val docRef: CollectionReference = dataBase.collection("airport")
        var startPoint: String = ""
        var arrivalPoint: String = ""
        val intent = intent
        val receiverInformation = HashMap<String, Any>()
        val airportList: ArrayList<String>
        airportList = intent.getStringArrayListExtra("airportList")
        val airportModelList = ArrayList<AirportModel>()
        var startCountry: String = ""
        var arrivalCountry: String = ""
        val sp = getSharedPreferences("userKey", Activity.MODE_PRIVATE)
        val key: String? = sp.getString("key", "not_key")

        docRef.get().addOnCompleteListener {
            if (it.isSuccessful) {
                val res = it.result
                res.mapTo(airportModelList) { AirportModel(name = it.data["name"].toString(), country = it.data["country"].toString(), position = it.data["position"].toString()) }
            }
        }
        receive_spinner_startPoint.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                startPoint = receive_spinner_startPoint.getItemAtPosition(p2).toString()
                Toast.makeText(baseContext, startPoint, Toast.LENGTH_SHORT).show()
            }

        }
        receive_spinner_arrivalPoint.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                arrivalPoint = receive_spinner_arrivalPoint.getItemAtPosition(p2).toString()
                Toast.makeText(baseContext, arrivalPoint, Toast.LENGTH_SHORT).show()
            }

        }

        val spinnerAdapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, airportList)
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        receive_spinner_startPoint.adapter = spinnerAdapter
        receive_spinner_arrivalPoint.adapter = spinnerAdapter

        receive_button_nextStep.setOnClickListener {
            airportModelList.forEach {
                if (it.name == startPoint) {
                    startCountry = it.country
                }
                if (it.name == arrivalPoint) {
                    arrivalCountry = it.country
                }
            }
            receiverInformation.put("start_point", startPoint)
            receiverInformation.put("arrival_point", arrivalPoint)
            receiverInformation.put("start_country", startCountry)
            receiverInformation.put("arrival_country", arrivalCountry)
            receiverInformation.put("user_id", key.toString())

            dataBase.collection("receiver")
                    .add(receiverInformation)
                    .addOnSuccessListener {
                        Toast.makeText(baseContext, "되네", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(baseContext, "안되네", Toast.LENGTH_SHORT).show()
                    }

        }
    }
}
