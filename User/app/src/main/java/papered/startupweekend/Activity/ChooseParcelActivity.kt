package papered.startupweekend.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.*
import kotlinx.android.synthetic.main.activity_choose_parcel.*
import kotlinx.android.synthetic.main.item_parcel.view.*
import papered.startupweekend.Model.ParcelModel
import papered.startupweekend.R
import papered.startupweekend.RecyclerAdapter.ParcelAdapter
import papered.startupweekend.RecyclerAdapter.ParcelListModel
import papered.startupweekend.Util.RecyclerItemClickListener

class ChooseParcelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_parcel)
        val intent = intent
        val dataBase = FirebaseFirestore.getInstance()
        val list: ArrayList<ParcelListModel> = ArrayList()
        var adapter : ParcelAdapter? = null
        Log.d("PARSE_RESULT", intent.getStringExtra("arrival_country"))
        Log.d("PARSE_RESULT", intent.getStringExtra("start_country"))
        val docRef: Task<QuerySnapshot> = dataBase.collection("parcel")
                .whereEqualTo("arrival_point", intent.getStringExtra("arrival_country"))
                .get()
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(baseContext, "Complete", Toast.LENGTH_SHORT).show()
                        Log.d("PARSE_RESULT", "${it.result.size()}")
                        for (document: QueryDocumentSnapshot in it.result) {
                            Toast.makeText(baseContext,"for",Toast.LENGTH_SHORT).show()
                            val data = document.data

//                            list.add(ParcelModel(approval = data["approval"].toString(), startingPoint = data["starting_point"].toString(), arrivalDetailAddress = data["arrival_detail_address"].toString(),
//                                    arrivalPoint = data["arrival_point"].toString(), itemStatus = data["item_status"].toString().toBoolean(), itemWeight = data["item_weight"].toString(), largeItemCount = data["large_item_count"].toString(),
//                                    middleItemCount = data["middle_item_count"].toString(), smallItemCount = data["small_item_count"].toString()))
                            list.add(ParcelListModel(startPoint = data["starting_point"].toString(), itemSize = "S: ${data["small_item_count"]}, M: ${data["middle_item_count"]}, L: ${data["large_item_count"]}",
                                    itemWeight = data["item_weight"].toString(), arrivalPoint = data["arrival_point"].toString(),id = document.id))
                            Log.d("PARSE_RES", document.data.toString())
                        }
                        adapter = ParcelAdapter(list)
                        choose_recyclerView_parcelList.adapter = adapter
                        adapter?.setItemClick(object : ParcelAdapter.ItemClick {
                            override fun onClick(view: View, position: Int) {
                                Toast.makeText(baseContext, list[position].id,Toast.LENGTH_SHORT).show()
//                                val intent = Intent(this, )
                            }

                        })
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(baseContext, "실패", Toast.LENGTH_SHORT).show()
                }
        Toast.makeText(baseContext,"${list.size}",Toast.LENGTH_SHORT).show()
        choose_recyclerView_parcelList.setHasFixedSize(true)
        adapter?.notifyDataSetChanged()
    }
}
