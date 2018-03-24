package papered.startupweekend.Activity

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
//        val dataBase = FirebaseFirestore.getInstance()
//        val docRef : CollectionReference = dataBase.collection("airport")
        var startPoint : String
        var arrivalPoint : String
        val intent = intent
//        val list = intent.getStringArrayExtra("list")
        val list = intent.getStringArrayListExtra("list")
//        docRef.get().addOnCompleteListener {
//            if (it.isSuccessful){
//                val res = it.result
//                res.mapTo(list) { it.data["name"].toString() }
//            }
//        }
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

        val spinnerAdapter = ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,list)
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        receive_spinner_startPoint.adapter = spinnerAdapter
        receive_spinner_arrivalPoint.adapter = spinnerAdapter
    }
}
