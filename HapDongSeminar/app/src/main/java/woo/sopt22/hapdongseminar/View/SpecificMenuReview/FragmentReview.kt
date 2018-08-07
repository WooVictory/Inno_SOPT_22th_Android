package woo.sopt22.hapdongseminar.View.SpecificMenuReview

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.fragment_review.*
import kotlinx.android.synthetic.main.fragment_review.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import woo.sopt22.hapdongseminar.Model.StoreReview
import woo.sopt22.hapdongseminar.Model.StoreReviewData
import woo.sopt22.hapdongseminar.Network.ApplicationController
import woo.sopt22.hapdongseminar.Network.NetworkService
import woo.sopt22.hapdongseminar.R
import woo.sopt22.hapdongseminar.base.utils.SharedPreferencesService

class FragmentReview : Fragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.menu_review_post_btn->{
                startActivity(Intent(context, MenuRegisterReviewActivity::class.java))
            }
        }
    }


    lateinit var networkService: NetworkService
    lateinit var menuReviewAdapter: MenuReviewAdapter
    lateinit var storeMenuReviewItems : ArrayList<StoreReviewData>
    lateinit var requestManager: RequestManager


    // 가려졌다가 다시 뜰 때
    override fun onStart() {
        super.onStart()

        getStoreReview()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_review,container,false)


        networkService = ApplicationController.instance!!.networkService
        SharedPreferencesService.instance!!.load(context!!)

        requestManager = Glide.with(context!!)

        view.menu_review_post_btn.setOnClickListener(this)

        getStoreReview()

        return view
    }


    fun getStoreReview(){
        val storeReview = networkService.getStoreReview(SharedPreferencesService.instance!!.getPrefIntegerData("store_idx"))
        storeReview.enqueue(object : Callback<StoreReview>{
            override fun onFailure(call: Call<StoreReview>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<StoreReview>?, response: Response<StoreReview>?) {
                if(response!!.isSuccessful){
                    storeMenuReviewItems = response!!.body().result
                    menuReviewAdapter = MenuReviewAdapter(storeMenuReviewItems, requestManager)
                    menu_review_recyclerview.layoutManager = LinearLayoutManager(context!!)
                    menu_review_recyclerview.adapter = menuReviewAdapter
                }
            }

        })
    }
}