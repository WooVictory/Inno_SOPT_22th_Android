package woo.sopt22.hapdongseminar.View.Detail

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView
import woo.sopt22.hapdongseminar.R

class DishListViewHolder(itemView : View?) : RecyclerView.ViewHolder(itemView) {

    var dishListImage : CircleImageView = itemView!!.findViewById(R.id.dish_list_item_image) as CircleImageView
    var dishListName : TextView = itemView!!.findViewById(R.id.dish_list_item_store_name) as  TextView
    var dishListIdx : TextView = itemView!!.findViewById(R.id.dish_list_item_store_idx) as  TextView
    var dishListContent : TextView = itemView!!.findViewById(R.id.dish_list_item_store_content) as TextView

}