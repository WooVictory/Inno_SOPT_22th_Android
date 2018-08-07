package woo.sopt22.seminar4r.View

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import woo.sopt22.seminar4r.Adapter.BoardAdapter
import woo.sopt22.seminar4r.Get.GetBoardResponse
import woo.sopt22.seminar4r.Get.GetBoardResponseData
import woo.sopt22.seminar4r.Network.ApplicationController
import woo.sopt22.seminar4r.Network.NetworkService
import woo.sopt22.seminar4r.R

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        val index = main_board_list!!.getChildAdapterPosition(v!!)
        val intent : Intent = Intent(applicationContext, BoardDetailActivity::class.java)

        intent.putExtra("user_id",boardItems[index].user_id)
        Log.v("board_idx가 무엇이지?",boardItems[index].board_idx.toString())
        intent.putExtra("content", boardItems[index].board_content)
        intent.putExtra("board_idx",boardItems[index].board_idx)
        intent.putExtra("board_pw",boardItems[index].board_pw)
        if(boardItems[index].board_photo !=null){
            intent.putExtra("image",boardItems[index].board_photo)
        }else{
            intent.putExtra("image","이미지 없음")
        }
        //Log.v("이미지확인",boardItems[index].board_photo)
        startActivity(intent)
    }

    lateinit var networkService: NetworkService
    lateinit var boardItems : ArrayList<GetBoardResponseData>
    // boardItems는 우리가 보는 뷰에 뿌릴 ArrayList이므로 message를 제외한 data배열[객체배열]이다.
    // 이 boardItems에 response의 data를 받으면 된다.
    lateinit var boardAdapter : BoardAdapter
    lateinit var requestManager : RequestManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        networkService = ApplicationController.instance.networkService
        // 네트워크 서비스 초기화

        main_post_btn.setOnClickListener {
            startActivity(Intent(applicationContext, BoardActivity::class.java))
        }

        main_board_list.layoutManager = LinearLayoutManager(this)
        requestManager = Glide.with(this)

        getBoard()

    }
    fun getBoard(){
        val getBoardResponse = networkService.getContent()
        // networkService의 getContent를 호출하면 Call 형태 즉, 리턴 타입은 GetBoardResponse가 된다.
        // 이것이 서버의 API를 보고 작성했을 때, 서버가 나한테 주는 타입이다.
        // message + data배열[객체 배열]
        getBoardResponse.enqueue(object : Callback<GetBoardResponse>{
            override fun onFailure(call: Call<GetBoardResponse>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<GetBoardResponse>?, response: Response<GetBoardResponse>?) {
                if (response!!.isSuccessful){
                    boardItems = response!!.body().data
                    boardAdapter = BoardAdapter(boardItems,requestManager)
                    boardAdapter.setOnItemClick(this@MainActivity)
                    main_board_list.adapter = boardAdapter
                }
            }

        })
    }

    override fun onRestart() {
        super.onRestart()
        getBoard()

    }
}
