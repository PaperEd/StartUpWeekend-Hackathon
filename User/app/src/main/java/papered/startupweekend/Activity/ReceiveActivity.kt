package papered.startupweekend.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_receive.*
import papered.startupweekend.Model.AirportModel
import papered.startupweekend.R

class ReceiveActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receive)
        val dataBase = FirebaseFirestore.getInstance()
        val docRef : CollectionReference = dataBase.collection("airport")
        val list = ArrayList<String>()
        docRef.get().addOnCompleteListener {
            if (it.isSuccessful){
                val res = it.result
                res.mapTo(list) { it.data["name"].toString() }
            }
        }

        val spinnerAdapter = ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,list)
        receive_spinner_startPoint.adapter = spinnerAdapter
        receive_spinner_arrivalPoint.adapter = spinnerAdapter
    }
}
