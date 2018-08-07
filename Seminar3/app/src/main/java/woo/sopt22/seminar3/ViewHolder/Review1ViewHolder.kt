package woo.sopt22.seminar3.ViewHolder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.review1_item.view.*
import woo.sopt22.seminar3.R

class Review1ViewHolder(itemView : View?) : RecyclerView.ViewHolder(itemView) {

    var busNumItem : TextView = itemView!!.findViewById(R.id.review1_bus_num_item)
    var busLocationItem : TextView = itemView!!.findViewById(R.id.review1_bus_location_item)
    var busColorItem : ImageView = itemView!!.findViewById(R.id.review1_bus_color_item)
}