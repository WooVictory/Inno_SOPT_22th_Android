package woo.sopt22.hapdongseminar.View.Bookmark

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.fragment_bookmark.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import woo.sopt22.hapdongseminar.Model.StoreListResponse
import woo.sopt22.hapdongseminar.Model.StoreListResponseData
import woo.sopt22.hapdongseminar.Network.ApplicationController
import woo.sopt22.hapdongseminar.Network.NetworkService
import woo.sopt22.hapdongseminar.R
import woo.sopt22.hapdongseminar.View.Detail.DishListAdapter
import woo.sopt22.hapdongseminar.View.SpecificStore.SpecificStoreActivity
import woo.sopt22.hapdongseminar.base.utils.SharedPreferencesService
import java.util.ArrayList

class BookMarkFragment : Fragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        val position = bookmark_recyclerView.getChildAdapterPosition(v!!)
        //ToaskMaker.makeLongToast(this, storeItems[position].store_idx.toString())
        // 여기서 나중에 상세보기로 들어갈 때 구현하면됨
        val intent = Intent(context, SpecificStoreActivity::class.java)
        intent.putExtra("store_name",bookmarkListItems[position].store_name)
        intent.putExtra("store_idx",bookmarkListItems[position].store_idx)
        intent.putExtra("bookmarkCheck",bookmarkListItems[position].bookmarkCheck)
        intent.putExtra("position",position)
        startActivity(intent)
    }

    lateinit var networkService: NetworkService
    lateinit var bookmarkListItems : ArrayList<StoreListResponseData>
    lateinit var requestManager: RequestManager
    lateinit var bookmarkListAdapter : DishListAdapter
    private lateinit var onItemClick : View.OnClickListener

    fun setOnItemClick(l : View.OnClickListener){
        onItemClick = l
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_bookmark, container, false)

        networkService = ApplicationController.instance!!.networkService
        SharedPreferencesService.instance!!.load(context!!)

        requestManager = Glide.with(context!!)

        getBookmarkList()
        return view
    }


    fun getBookmarkList(){
        val bookmarkList = networkService.getBookmark(SharedPreferencesService.instance!!.getPrefStringData("user_id")!!)
        bookmarkList.enqueue(object : Callback<StoreListResponse>{
            override fun onFailure(call: Call<StoreListResponse>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<StoreListResponse>?, response: Response<StoreListResponse>?) {
                if(response!!.isSuccessful){
                    bookmarkListItems = response!!.body().result
                    bookmark_recyclerView.layoutManager = LinearLayoutManager(context!!)
                    bookmarkListAdapter = DishListAdapter(bookmarkListItems, requestManager)
                    bookmarkListAdapter.setOnItemClick(this@BookMarkFragment)
                    bookmark_recyclerView.adapter = bookmarkListAdapter


                }
            }

        })

    }
}


