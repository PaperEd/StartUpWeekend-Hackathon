package papered.startupweekend.Fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_main.view.*
import papered.startupweekend.Activity.ReceiveActivity
import papered.startupweekend.Activity.SendingParcelActivity
import papered.startupweekend.Activity.ShowPriceActivity
import papered.startupweekend.Activity.WarningActivity

import papered.startupweekend.R

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [MainFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment() : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        val dataBase = FirebaseFirestore.getInstance()
        val docRef : CollectionReference = dataBase.collection("airport")
        var startPoint : String
        var arrivalPoint : String
        val list = ArrayList<String>()
        docRef.get().addOnCompleteListener {
            if (it.isSuccessful){
                val res = it.result
                res.mapTo(list) { it.data["name"].toString() }
            }
        }

        view.mainFragment_sendingPost.setOnClickListener {
            startActivity(Intent(context,WarningActivity::class.java))
        }
        view.mainFragment_receivePost.setOnClickListener {
            val intent = Intent(context,ReceiveActivity::class.java)
            intent.putStringArrayListExtra("list",list)
            startActivity(intent)
        }
        return view
    }

}
