package woo.sopt22.seminar4r.View

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_board_detail.*
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Comment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import woo.sopt22.seminar4r.Adapter.CommentAdapter
import woo.sopt22.seminar4r.Delete.DeleteBoardRequest
import woo.sopt22.seminar4r.Delete.DeleteBoardResponse
import woo.sopt22.seminar4r.Get.GetCommentResponse
import woo.sopt22.seminar4r.Get.GetCommentResponseData
import woo.sopt22.seminar4r.Network.ApplicationController
import woo.sopt22.seminar4r.Network.NetworkService
import woo.sopt22.seminar4r.R

class BoardDetailActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?){
        val index = boardDetail_comment_list!!.getChildAdapterPosition(v!!)
        val intent = Intent(applicationContext, CommentDeleteActivity::class.java)
        intent.putExtra("comment_idx",commentItems[index].comment_idx)
        startActivity(intent)


    }

    lateinit var networkService: NetworkService
    lateinit var commentItems : ArrayList<GetCommentResponseData>
    lateinit var commentAdapter: CommentAdapter
    lateinit var deleteBoardRequestData: DeleteBoardRequest
    lateinit var board_pw : String
    var boardIdx : Int?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_detail)

        networkService = ApplicationController.instance.networkService

        boardDetail_user_id.text = intent.getStringExtra("user_id")
        boardDetail_content.text = intent.getStringExtra("content")
        boardIdx = intent.getIntExtra("board_idx",0)
        board_pw = intent.getStringExtra("board_pw")
        getComment()
        if(intent.getStringExtra("image") ==null) {
            boardDetail_image.setImageResource(R.drawable.pic11)

        }else{
            Glide.with(this)
                    .load(intent.getStringExtra("image"))
                    .into(boardDetail_image)

        }
        Log.v("이미지가 어디있죵",intent.getIntExtra("image",0).toString())

        boardDetail_comment_post_btn.setOnClickListener{
            val intent = Intent(applicationContext, CommentPostActivity::class.java)
            intent.putExtra("board_idx",boardIdx!!)
            startActivity(intent)
        }

        boardDetail_post_delete.setOnClickListener{
            deleteBoard()
        }






    }

    // 게시판 글 상세보기에서 댓글 보기 구현
    fun getComment(){
        Log.v("왜안들어요냐","들어와라")
        val getCommentResponse = networkService.getComment(boardIdx!!)
        Log.v("2031",boardIdx.toString())
        getCommentResponse.enqueue(object : Callback<GetCommentResponse>{
            override fun onFailure(call: Call<GetCommentResponse>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<GetCommentResponse>?, response: Response<GetCommentResponse>?) {
                if(response!!.isSuccessful){
                    Log.v("2031",response!!.body().message)
                    commentItems = response!!.body().data
                    commentAdapter = CommentAdapter(commentItems)
                    commentAdapter.setOnItemClick(this@BoardDetailActivity)
                    boardDetail_comment_list.layoutManager = LinearLayoutManager(this@BoardDetailActivity)
                    boardDetail_comment_list.adapter = commentAdapter
                }
            }

        })

    }
    // 게시판 글 삭제
    fun deleteBoard(){
        deleteBoardRequestData = DeleteBoardRequest(boardIdx!!, board_pw!!)
        val deleteBoardRequest =  networkService.deleteBoard(deleteBoardRequestData)
        deleteBoardRequest.enqueue(object : Callback<DeleteBoardResponse>{
            override fun onFailure(call: Call<DeleteBoardResponse>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<DeleteBoardResponse>?, response: Response<DeleteBoardResponse>?) {
                if(response!!.isSuccessful){
                    finish()
                }
            }

        })

    }


    override fun onRestart() {
        super.onRestart()
        Toast.makeText(applicationContext, "여기가 뜨자 Restart",Toast.LENGTH_LONG).show()

        getComment()
        /*comment_board_idx = intent.getIntExtra("comment_board_idx",0)
        val getCommentResponseOneMore = networkService.getComment(comment_board_idx!!)
        getCommentResponseOneMore.enqueue(object : Callback<GetCommentResponse>{
            override fun onFailure(call: Call<GetCommentResponse>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<GetCommentResponse>?, response: Response<GetCommentResponse>?) {
                if(response!!.isSuccessful){
                    commentItems = response!!.body().data
                    commentAdapter = CommentAdapter(commentItems)
                    boardDetail_comment_list.layoutManager = LinearLayoutManager(this@BoardDetailActivity)
                    boardDetail_comment_list.adapter = commentAdapter
                }
            }

        })*/

    }
}
