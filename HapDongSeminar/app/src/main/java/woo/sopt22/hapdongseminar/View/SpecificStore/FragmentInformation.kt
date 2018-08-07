package woo.sopt22.hapdongseminar.View.SpecificStore

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_information.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import woo.sopt22.hapdongseminar.Model.StoreInfo
import woo.sopt22.hapdongseminar.Network.ApplicationController
import woo.sopt22.hapdongseminar.Network.NetworkService
import woo.sopt22.hapdongseminar.R
import woo.sopt22.hapdongseminar.base.utils.SharedPreferencesService

class FragmentInformation : Fragment() {

    lateinit var networkService: NetworkService
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_information,container,false)

        networkService = ApplicationController.instance!!.networkService
        SharedPreferencesService.instance!!.load(context!!)
        getStoreInformation()
        return view
    }
    fun getStoreInformation(){
        val storeInformation = networkService.getStoreInfo(SharedPreferencesService.instance!!.getPrefIntegerData("store_idx"))
        storeInformation.enqueue(object : Callback<StoreInfo>{
            override fun onFailure(call: Call<StoreInfo>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<StoreInfo>?, response: Response<StoreInfo>?) {
                if(response!!.isSuccessful){
                    Glide.with(context!!).load(response!!.body().result[0].store_photo).into(menu_information_image)
                    menu_review_count.text = response!!.body().result[0].review_count.toString()
                    menu_information_store_explain.text = response!!.body().result[0].store_content

                }
            }

        })

    }

}