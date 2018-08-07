package woo.sopt22.hapdongseminar.View.Detail

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import woo.sopt22.hapdongseminar.Model.StoreListResponseData
import woo.sopt22.hapdongseminar.R

class DishListAdapter(var storeItems : ArrayList<StoreListResponseData>
                      , var requestManager: RequestManager)
    : RecyclerView.Adapter<DishListViewHolder>() {

    private lateinit var onItemClick : View.OnClickListener

    fun setOnItemClick(l : View.OnClickListener){
        onItemClick = l
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishListViewHolder {
        val mainView = LayoutInflater.from(parent.context).inflate(R.layout.dish_list_item, parent
                ,false)
        mainView.setOnClickListener(onItemClick)
        return DishListViewHolder(mainView)

    }

    override fun getItemCount(): Int = storeItems!!.size

    override fun onBindViewHolder(holder: DishListViewHolder, position: Int) {
        holder.dishListContent.text = storeItems[position].store_content
        Log.v("adapter",storeItems[position].store_content)
        //holder.dishListName.text = "왜안되냐 빡치게"
        holder.dishListIdx.text = storeItems[position].store_idx.toString()
        holder.dishListName.text = storeItems[position].store_name
        requestManager.load(storeItems[position].store_photo).into(holder.dishListImage)
        // 북마크 색칠되는거 여기서 처리해야 할 듯??
        
    }
}