package woo.sopt22.hapdongseminar.View.SpecificMenuReview

import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.RequestManager
import woo.sopt22.hapdongseminar.Model.StoreReviewData
import woo.sopt22.hapdongseminar.R

class MenuReviewAdapter(var storeMenuReviewItems : ArrayList<StoreReviewData>
                        , var requestManager: RequestManager)
    : RecyclerView.Adapter<MenuReviewAdapter.MenuReviewViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuReviewViewHolder {

        val mainView = LayoutInflater.from(parent.context).inflate(R.layout.item_cardview, parent,false)

        return MenuReviewViewHolder(mainView)

    }

    override fun getItemCount(): Int = storeMenuReviewItems.size

    override fun onBindViewHolder(holder: MenuReviewViewHolder, position: Int) {
        holder.reviewUserId.text = storeMenuReviewItems[position].user_id
        holder.reviewDate.text = storeMenuReviewItems[position].review_time
        requestManager.load(storeMenuReviewItems[position].review_photo).into(holder.reviewImage)
        holder.reviewContent.text = storeMenuReviewItems[position].review_content
    }


    class MenuReviewViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val reviewUserId : TextView = itemView.findViewById(R.id.cardView_id)
        val reviewDate : TextView = itemView.findViewById(R.id.cardView_date)
        val reviewImage : ImageView = itemView.findViewById(R.id.cardView_image)
        val reviewContent : TextView = itemView.findViewById(R.id.cardView_content)

    }
}