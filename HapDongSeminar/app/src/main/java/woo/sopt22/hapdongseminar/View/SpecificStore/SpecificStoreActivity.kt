package woo.sopt22.hapdongseminar.View.SpecificStore

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import kotlinx.android.synthetic.main.activity_specific_store.*
import woo.sopt22.hapdongseminar.R
import android.support.design.widget.TabLayout
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.View
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import woo.sopt22.hapdongseminar.Model.BookmarkRegister
import woo.sopt22.hapdongseminar.Network.ApplicationController
import woo.sopt22.hapdongseminar.Network.NetworkService
import woo.sopt22.hapdongseminar.base.BaseModel
import woo.sopt22.hapdongseminar.base.utils.SharedPreferencesService
import woo.sopt22.hapdongseminar.base.utils.ToaskMaker


class SpecificStoreActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            specific_store_back_btn->{
                finish()
            }
            specific_store_bookmark_btn->{
                // 북마크 통시 여기서 하면 됨
                if(isLike!!){ // isLike가 true라면 좋아요 버튼 누를 수 있도록
                    postBookmark()
                } else { // false 일 경우
                    deleteBookmark()
                }

                Log.v("119","여기는 들어오니")
            }
        }
    }

    lateinit var pageAdapter: PageAdapter
    lateinit var networkService: NetworkService
    lateinit var bookmarkRegister: BookmarkRegister
    var isLike : Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specific_store)

        getData() // 인텐트로 넘어오는 데이터 받는 함수
        addFragment(FragmentMenu())
        SharedPreferencesService.instance!!.load(this)
        networkService = ApplicationController.instance!!.networkService

        // TabLayout을 이용해서 tab 이름을 설정하는 부분

        specific_store_tab.addTab(specific_store_tab.newTab().setText("메뉴"))
        specific_store_tab.addTab(specific_store_tab.newTab().setText("정보"))
        specific_store_tab.addTab(specific_store_tab.newTab().setText("리뷰"))

        specific_store_tab.tabGravity = TabLayout.GRAVITY_FILL

        pageAdapter = PageAdapter(supportFragmentManager, specific_store_tab.tabCount)
        specific_store_viewpager.adapter = pageAdapter

        specific_store_viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(specific_store_tab))
        specific_store_tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) { // Tab이 선택되었을 때
                specific_store_viewpager.setCurrentItem(tab!!.position)
            }

        })


        // 클릭 리스너를 달아준다. 버튼에!
        specific_store_back_btn.setOnClickListener(this)
        specific_store_bookmark_btn.setOnClickListener(this)

    }


    // 북마크 삭제
    fun deleteBookmark(){
        bookmarkRegister = BookmarkRegister(
                SharedPreferencesService.instance!!.getPrefIntegerData("store_idx",0)
                , SharedPreferencesService.instance!!.getPrefStringData("user_id")!!)
        val deleteBookmark = networkService.deleteBookmark(bookmarkRegister)
        deleteBookmark.enqueue(object : Callback<BaseModel>{
            override fun onFailure(call: Call<BaseModel>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<BaseModel>?, response: Response<BaseModel>?) {
                if(response!!.isSuccessful){
                    Log.v("2339",response!!.body().message)
                    bookmarkCheck()
                }
            }

        })
    }
    fun bookmarkCheck(){
        if(isLike!!){
            specific_store_bookmark_btn.setImageResource(R.drawable.like_on)
            isLike = false
        } else if(!isLike!!){
            specific_store_bookmark_btn.setImageResource(R.drawable.like_off)
            isLike = true
        }
    }


    // 북마크 등록
    fun postBookmark(){
        bookmarkRegister = BookmarkRegister(
                SharedPreferencesService.instance!!.getPrefIntegerData("store_idx",0)
                , SharedPreferencesService.instance!!.getPrefStringData("user_id")!!)
        Log.v("2116",SharedPreferencesService.instance!!.getPrefIntegerData("store_idx",0).toString())
        Log.v("2116",SharedPreferencesService.instance!!.getPrefStringData("user_id"))
        val postBookmark = networkService.postBookmark(bookmarkRegister)
        postBookmark.enqueue(object : Callback<BaseModel>{
            override fun onFailure(call: Call<BaseModel>?, t: Throwable?) {
                Log.v("2118",t!!.message)
            }

            override fun onResponse(call: Call<BaseModel>?, response: Response<BaseModel>?) {
                if(response!!.isSuccessful){
                    Log.v("2119",response!!.body().message)
                    bookmarkCheck()


                }
            }
        })


    }

    // ViewPager에 Fragment를 붙이는 함수
    fun addFragment(fragment : Fragment){
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.add(R.id.specific_store_viewpager, fragment)
        transaction.commit()
    }

    fun replaceFragment(fragment : Fragment){
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.replace(R.id.specific_store_viewpager, fragment)
        transaction.commit()

    }

    fun getData(){
        val storeIndex = getIntent()
        val storeName = getIntent()
        val storeBookmark = getIntent()
        //ToaskMaker.makeShortToast(applicationContext, storeIndex.toString())
        Log.v("1259",storeIndex.getIntExtra("store_idx",0).toString())
        SharedPreferencesService.instance!!.setPrefData("store_idx",storeIndex.getIntExtra("store_idx",0))
        specific_store_toolbar_tv.text = storeName.getStringExtra("store_name")
        if(storeBookmark.getBooleanExtra("bookmarkCheck",true)){
            specific_store_bookmark_btn.setImageResource(R.drawable.like_on)
        } else if(storeBookmark.getBooleanExtra("bookmarkCheck",false)){
            specific_store_bookmark_btn.setImageResource(R.drawable.like_off)
        }
    }

}
