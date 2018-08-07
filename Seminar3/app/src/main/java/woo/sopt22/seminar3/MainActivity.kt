package woo.sopt22.seminar3

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.kakao_item.*
import woo.sopt22.seminar3.Adapter.KaKaoAdapter
import woo.sopt22.seminar3.Data.KaKaoData

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {

        val index : Int = main_recyclerview.getChildAdapterPosition(v!!) // 몇번째를 클릭했는지에 대한 정보를 index가 가지고 있다.
        val name : String = kakaoItems[index].name
        val profile : Int = kakaoItems[index].profile

        val intent : Intent = Intent(applicationContext, ChatActivity::class.java)
        intent.putExtra("name",name)
        intent.putExtra("profile",profile)
        startActivity(intent)


        // 리사이클러뷰의 몇번째를 클릭했는지 처리할 것


    }

    lateinit var kakaoItems : ArrayList<KaKaoData>
    var kakaoItems2 : ArrayList<KaKaoData> = ArrayList()
    var kakaoItems3 : ArrayList<KaKaoData>?=null
    // 3가지 방법이 있음
    var context : Context = this

    var isDisplayButtons : Boolean = false

    lateinit var kakaoAdapter: KaKaoAdapter
    lateinit var swipeController: SwipeController
    lateinit var itemTouchListener : ItemTouchHelper
    /*FIXME
    * lateinit : 초기화를 지금하지 않고 나중에 초기화를 진행할 수 있도록 하는 방법
    * */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        kakaoItems = ArrayList()
        kakaoItems.add(KaKaoData(R.drawable.pic1,"09의 바나나 안드로이드","낰낰","오후 4:07"))
        kakaoItems.add(KaKaoData(R.drawable.pic2," 이돌이의 차근차근 기",":D ><","오후 6:05"))
        kakaoItems.add(KaKaoData(R.drawable.pic3,"트카의 텔미텔미딪","아니 내","오후 3:07"))
        kakaoItems.add(KaKaoData(R.drawable.pic4,"사과의 고속사과","이상하고만","오후 8:24"))
        kakaoItems.add(KaKaoData(R.drawable.pic5,"섭이의 섭섭한 칼","옆모습 정승환","오후 11:09"))
        kakaoItems.add(KaKaoData(R.drawable.pic6,"인누강의 웹마이웨이","호에에에엥","오전 7:59"))
        kakaoItems.add(KaKaoData(R.drawable.pic7,"신선이의 힐링캠프","애들아...딱 그 5분만 할게요","오후 6:27"))
        kakaoItems.add(KaKaoData(R.drawable.pic8,"할머니의 당찬하루","아..!","오후 3:33"))
        kakaoItems.add(KaKaoData(R.drawable.pic9,"이모님의 회계원리","뒤풀이 어디가지...","오후 11:55"))
        kakaoItems.add(KaKaoData(R.drawable.pic10,"대장의 생방송","따봉따봉미 bb","오후 10:10"))
        // 코틀린은 new라는 키워드가 존재하지 않기 때문에 바로 생성자를 넣어주면 된다.



        kakaoAdapter = KaKaoAdapter(kakaoItems,context)
        kakaoAdapter.setOnItemClickListener(this)
        main_recyclerview.layoutManager = LinearLayoutManager(this)
        main_recyclerview.adapter = kakaoAdapter

        swipeController = SwipeController() // 초기화
        itemTouchListener = ItemTouchHelper(swipeController)
        itemTouchListener.attachToRecyclerView(main_recyclerview)


        // 리사이클러뷰 아이템에 그림을 그려줄 것

        main_recyclerview.addItemDecoration(object : RecyclerView.ItemDecoration(){

            override fun onDraw(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
                swipeController.onDraw(c!!)
            }
        })

        main_float_add.setOnClickListener {
            clickFloat()
        }
        main_float_category1.setOnClickListener {
            // 여기서 그냥 이벤트 넣어주면 된다.
        }

    }

    // 버튼을 누를때마다 호출되는 함수

    fun clickFloat(){
        if(!isDisplayButtons){ // false일 경우
            // 평소 상태. 버튼이 보여지지 않고 있는 상태

            isDisplayButtons = true

            main_wrapper_layout.visibility = View.VISIBLE
            main_wrapper_layout.setOnClickListener{
                clickFloat()
            }

            val animation = AnimationUtils.loadAnimation(this, R.anim.float_main_show)
            main_float_add.setBackgroundResource(R.drawable.pic4)
            // 아무것도 누르지 않은 상태에서 add를 눌렀을 때 보여지는 액션이 나오면서
            // 그 때 버튼의 background가 취소 버튼으로 보여지는 액션을 취한다.
            main_float_add.startAnimation(animation) // 애니메이션 등록

            val layoutParam1 = main_float_category1.layoutParams as RelativeLayout.LayoutParams // Activity에서 xml을 다루어야 할 때 layoutParam을 자주 사용한다.
            // 위는 부모 레이아웃의 layoutParam이라는 객체를 통해서 접근한다.
            layoutParam1.bottomMargin +=(main_float_category1.height * 1.2).toInt()



            val showC1 =  AnimationUtils.loadAnimation(this, R.anim.float_button1_show)
            main_float_category1.layoutParams = layoutParam1
            main_float_category1.startAnimation(showC1)
            main_float_category1.isClickable = true // 클릭할 수 있게끔


            val layoutParam2 = main_float_category2.layoutParams as RelativeLayout.LayoutParams // Activity에서 xml을 다루어야 할 때 layoutParam을 자주 사용한다.
            // 위는 부모 레이아웃의 layoutParam이라는 객체를 통해서 접근한다.
            layoutParam2.bottomMargin +=(main_float_category2.height * 2.4).toInt()



            val showC2 =  AnimationUtils.loadAnimation(this, R.anim.float_button2_show)
            main_float_category2.layoutParams = layoutParam2
            main_float_category2.startAnimation(showC2)
            main_float_category2.isClickable = true // 클릭할 수 있게끔





        } else{ // true일 경우
            // 버튼이 보여지고 있는 상태

            isDisplayButtons = false

            main_wrapper_layout.visibility = View.INVISIBLE

            val animation = AnimationUtils.loadAnimation(this, R.anim.float_main_hide)
            main_float_add.setBackgroundResource(R.drawable.pic1)
            // 아무것도 누르지 않은 상태에서 add를 눌렀을 때 보여지는 액션이 나오면서
            // 그 때 버튼의 background가 취소 버튼으로 보여지는 액션을 취한다.
            main_float_add.startAnimation(animation) // 애니메이션 등록

            val layoutParam1 = main_float_category1.layoutParams as RelativeLayout.LayoutParams // Activity에서 xml을 다루어야 할 때 layoutParam을 자주 사용한다.
            // 위는 부모 레이아웃의 layoutParam이라는 객체를 통해서 접근한다.
            layoutParam1.bottomMargin -=(main_float_category1.height * 1.2).toInt()



            val showC1 =  AnimationUtils.loadAnimation(this, R.anim.float_button1_hide)
            main_float_category1.layoutParams = layoutParam1
            main_float_category1.startAnimation(showC1)
            main_float_category1.isClickable = false // 클릭할 수 있게끔


            val layoutParam2 = main_float_category2.layoutParams as RelativeLayout.LayoutParams // Activity에서 xml을 다루어야 할 때 layoutParam을 자주 사용한다.
            // 위는 부모 레이아웃의 layoutParam이라는 객체를 통해서 접근한다.
            layoutParam2.bottomMargin -=(main_float_category2.height * 2.4).toInt()



            val showC2 =  AnimationUtils.loadAnimation(this, R.anim.float_button2_hide)
            main_float_category2.layoutParams = layoutParam2
            main_float_category2.startAnimation(showC2)
            main_float_category2.isClickable = false // 클릭할 수 있게끔




        }
    }
}
