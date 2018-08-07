package woo.sopt22.seminar4r.View

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_comment_post.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import woo.sopt22.seminar4r.Network.ApplicationController
import woo.sopt22.seminar4r.Network.NetworkService
import woo.sopt22.seminar4r.Post.PostCommentRequest
import woo.sopt22.seminar4r.Post.PostCommentResponse
import woo.sopt22.seminar4r.R

class CommentPostActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var networkService: NetworkService
    lateinit var postComment : PostCommentRequest
    var board_idx : Int?=null
    override fun onClick(v: View?) {
        when(v!!){
            comment_post_btn->{
                commentPost()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment_post)

        networkService = ApplicationController.instance.networkService
        comment_post_btn.setOnClickListener(this)

        board_idx = intent.getIntExtra("board_idx",0)


    }
    fun commentPost(){

        postComment = PostCommentRequest(comment_post_user_id.text.toString(), board_idx!!, comment_post_content.text.toString(), comment_post_password.text.toString())
        val postCommentRequest = networkService.postComment(postComment)
        postCommentRequest.enqueue(object : Callback<PostCommentResponse>{
            override fun onFailure(call: Call<PostCommentResponse>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<PostCommentResponse>?, response: Response<PostCommentResponse>?) {
                if(response!!.isSuccessful){
                    Log.v("성공ㅇ냐 댓글은?",response!!.body().message)
                  /*  val intent = Intent(applicationContext, BoardDetailActivity::class.java)
                    intent.putExtra("comment_board_idx",board_idx!!)
                    startActivity(intent)*/
                   // startActivity(Intent(applicationContext, BoardDetailActivity::class.java))
                    finish()
                }
            }

        })
    }
}
