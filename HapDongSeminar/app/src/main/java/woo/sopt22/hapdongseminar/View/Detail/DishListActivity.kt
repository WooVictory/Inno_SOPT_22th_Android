package woo.sopt22.hapdongseminar.View.Detail

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.activity_dish_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import woo.sopt22.hapdongseminar.MainActivity
import woo.sopt22.hapdongseminar.Model.StoreCategory
import woo.sopt22.hapdongseminar.Model.StoreListResponse
import woo.sopt22.hapdongseminar.Model.StoreListResponseData
import woo.sopt22.hapdongseminar.Network.ApplicationController
import woo.sopt22.hapdongseminar.Network.NetworkService
import woo.sopt22.hapdongseminar.R
import woo.sopt22.hapdongseminar.View.Register.RegisterDishActivity
import woo.sopt22.hapdongseminar.View.SpecificStore.SpecificStoreActivity
import woo.sopt22.hapdongseminar.base.utils.SharedPreferencesService
import woo.sopt22.hapdongseminar.base.utils.ToaskMaker

class DishListActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            dish_list_back_btn->{ // 뒤로 가기 버튼
                finish()
            }
            dish_list_plus_btn->{ // 추가 하기 버튼
                //ToaskMaker.makeShortToast(this, "추가하기 버튼입니다.")
                startActivity(Intent(applicationContext, RegisterDishActivity::class.java))
            }
            dish_list_home_btn->{ // Home 버튼
                val intent = Intent(applicationContext, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
            dish_list_bookmark_btn->{ // 북마크 버튼

            }
            v!!->{
                val position = dish_list.getChildAdapterPosition(v!!)
                //ToaskMaker.makeLongToast(this, storeItems[position].store_idx.toString())
                // 여기서 나중에 상세보기로 들어갈 때 구현하면됨
                val intent = Intent(applicationContext, SpecificStoreActivity::class.java)
                intent.putExtra("store_name",storeItems[position].store_name)
                intent.putExtra("store_idx",storeItems[position].store_idx)
                intent.putExtra("bookmarkCheck",storeItems[position].bookmarkCheck)
                intent.putExtra("position",position)
                startActivity(intent)
            }
        }
    }

    var category : String?=null
    lateinit var networkService: NetworkService
    lateinit var storeItems : ArrayList<StoreListResponseData>
    lateinit var dishListAdapter: DishListAdapter
    lateinit var requestManager: RequestManager
    lateinit var storeCategory: StoreCategory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dish_list)

        networkService = ApplicationController.instance.networkService
        requestManager = Glide.with(this)

        dish_list.layoutManager = LinearLayoutManager(this)


        getData()
        Log.v("229",category.toString())
        SharedPreferencesService.instance!!.load(this)
        var name : String? = SharedPreferencesService.instance!!.getPrefStringData("user_id")
        Log.v("229 아이디",name!!)
        println(name!!)
        getStoreList(category!!)


        dish_list_plus_btn.setOnClickListener(this)
        dish_list_back_btn.setOnClickListener(this)
        dish_list_home_btn.setOnClickListener(this)
        dish_list_bookmark_btn.setOnClickListener(this)


    }

    // 카테고리별 StoreList 불러오기
    fun getStoreList(num : String){
        Log.v("1925","들오??")
        storeCategory = StoreCategory(SharedPreferencesService.instance!!.getPrefStringData("user_id")!!,num!!)
        val getStoreList = networkService.postStoreList(storeCategory)
        getStoreList.enqueue(object : Callback<StoreListResponse>{
            override fun onFailure(call: Call<StoreListResponse>?, t: Throwable?) {
                Log.v("fail","fail??")
            }

            override fun onResponse(call: Call<StoreListResponse>?,
                                    response: Response<StoreListResponse>?) {
                if(response!!.isSuccessful){
                    storeItems = response!!.body().result
                    dishListAdapter = DishListAdapter(storeItems, requestManager)
                    dishListAdapter.setOnItemClick(this@DishListActivity)
                    dish_list.adapter = dishListAdapter
                }

            }
        })

    }

    fun getData(){
        val intentCategoryNum = getIntent()
        val intentCategoryName = getIntent()
        category = intentCategoryNum.getStringExtra("result")
        dish_list_toolbar_tv.text = intentCategoryName.getStringExtra("category_name")
    }

    override fun onRestart() {
        super.onRestart()
        getStoreList(category!!)
    }



}
