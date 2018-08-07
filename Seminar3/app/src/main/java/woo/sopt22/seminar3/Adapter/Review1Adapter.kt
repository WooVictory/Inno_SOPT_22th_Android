package woo.sopt22.seminar3.Adapter

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import woo.sopt22.seminar3.Data.Review1Data
import woo.sopt22.seminar3.R
import woo.sopt22.seminar3.ViewHolder.Review1ViewHolder

class Review1Adapter(private var review1Data : ArrayList<Review1Data>) : RecyclerView.Adapter<Review1ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Review1ViewHolder {
        val mainView : View = LayoutInflater.from(parent.context).inflate(R.layout.review1_item,parent,false)
        return Review1ViewHolder(mainView)

    }

    override fun getItemCount() : Int = review1Data.size


    override fun onBindViewHolder(holder: Review1ViewHolder, position: Int) {
        holder.busNumItem.text = review1Data[position].busNumber
        holder.busNumItem.setTextColor(review1Data[position].busColor)
        holder.busLocationItem.text = review1Data[position].busLocation
        holder.busColorItem.setColorFilter(review1Data[position].busColor)

        //Log.v("0244",review1Data[position].busColor.toString())
    }
}