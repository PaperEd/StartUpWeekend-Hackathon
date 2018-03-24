package papered.startupweekend.RecyclerAdapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import papered.startupweekend.R
import papered.startupweekend.Util.RecyclerViewClickListener
import papered.startupweekend.RecyclerAdapter.ParcelAdapter.ItemClick


/**
 * Created by PaperEd on 2018-03-25.
 */
class ParcelAdapter() : RecyclerView.Adapter<ParcelAdapter.ParcelViewHolder>() {
    lateinit var items: ArrayList<ParcelListModel>

    constructor(items: ArrayList<ParcelListModel>) : this() {
        this.items = items
    }

    private var itemClick: ItemClick? = null
    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    fun setItemClick(itemClick: ItemClick) {
        this.itemClick = itemClick
    }
    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: ParcelViewHolder?, position: Int) {
        holder!!.startPoint.text = items.get(position).startPoint
        holder.arrivalPoint.text = items.get(position).arrivalPoint
        holder.itemSize.text = items.get(position).itemSize
        holder.itemWeight.text = items.get(position).itemWeight

        holder.itemView.setOnClickListener {
            if(itemClick != null){
                itemClick!!.onClick(it, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ParcelViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_parcel, parent, false)
        return ParcelViewHolder(view)
    }

    class ParcelViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val startPoint: TextView = itemView!!.findViewById(R.id.parcelItem_startPoint)
        val arrivalPoint: TextView = itemView!!.findViewById(R.id.parcelItem_arrivalPoint)
        val itemSize: TextView = itemView!!.findViewById(R.id.parcelItem_size)
        val itemWeight: TextView = itemView!!.findViewById(R.id.parcelItem_weight)
        override fun onClick(p0: View?) {
            p0?.setOnClickListener(this)
            Toast.makeText(p0?.context,startPoint.text.toString(),Toast.LENGTH_SHORT).show()
        }
    }
}

data class ParcelListModel(val startPoint: String, val arrivalPoint: String, val itemSize: String, val itemWeight: String, val id: String)

