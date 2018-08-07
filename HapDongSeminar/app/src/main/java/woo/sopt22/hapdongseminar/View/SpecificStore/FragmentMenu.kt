package woo.sopt22.hapdongseminar.View.SpecificStore

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_menu.*
import kotlinx.android.synthetic.main.fragment_menu.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import woo.sopt22.hapdongseminar.Model.StoreMenu
import woo.sopt22.hapdongseminar.Model.StoreMenuData
import woo.sopt22.hapdongseminar.Network.ApplicationController
import woo.sopt22.hapdongseminar.Network.NetworkService
import woo.sopt22.hapdongseminar.R
import woo.sopt22.hapdongseminar.base.utils.SharedPreferencesService

class FragmentMenu : Fragment() {


    lateinit var menuAdapter : MenuAdapter
    lateinit var networkService: NetworkService
    lateinit var menuItems : ArrayList<StoreMenuData>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_menu,container,false)

        networkService = ApplicationController.instance!!.networkService // 초기화
        menuItems = ArrayList<StoreMenuData>()



        SharedPreferencesService.instance!!.load(context!!)
        val storeIdx = SharedPreferencesService.instance!!.getPrefIntegerData("store_idx")
        Log.v("7000",storeIdx.toString())
        getStoreMenuList()



/*        menuAdapter = MenuAdapter(menuItems)
        view.fragmentMenuRecyclerView.layoutManager = LinearLayoutManager(context)
        view.fragmentMenuRecyclerView.adapter = menuAdapter*/

        return view
    }

    fun getStoreMenuList(){
        Log.v("7001","들어")
        val getStoreList = networkService.getStoreMenu(SharedPreferencesService.instance!!.getPrefIntegerData("store_idx"))
        getStoreList.enqueue(object : Callback<StoreMenu>{
            override fun onFailure(call: Call<StoreMenu>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<StoreMenu>?, response: Response<StoreMenu>?) {
                if(response!!.isSuccessful){
                    Log.v("7002",response!!.body().message)
                    menuItems = response!!.body().result
                    menuAdapter = MenuAdapter(menuItems)
                    fragmentMenuRecyclerView.layoutManager = LinearLayoutManager(context)
                    fragmentMenuRecyclerView.adapter=menuAdapter
                }
            }

        })


    }

}