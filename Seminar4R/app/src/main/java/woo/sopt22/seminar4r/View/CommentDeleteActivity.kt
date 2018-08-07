package woo.sopt22.seminar4r.View

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_comment_delete.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import woo.sopt22.seminar4r.Delete.DeleteCommentRequest
import woo.sopt22.seminar4r.Delete.DeleteCommentResponse
import woo.sopt22.seminar4r.Network.ApplicationController
import woo.sopt22.seminar4r.Network.NetworkService
import woo.sopt22.seminar4r.R

class CommentDeleteActivity : AppCompatActivity() {

    lateinit var networkService: NetworkService
    lateinit var deleteCommentRequestObject: DeleteCommentRequest
    var comment_idx : Int?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment_delete)

        networkService = ApplicationController.instance.networkService

        comment_idx = intent.getIntExtra("comment_idx",0)
        comment_delete_btn.setOnClickListener {
            deleteComment()
        }

    }

    private fun deleteComment() {
        deleteCommentRequestObject = DeleteCommentRequest(comment_idx!!, comment_delete_pw.text.toString())
        val deleteCommenRequest = networkService.deleteComment(deleteCommentRequestObject)
        deleteCommenRequest.enqueue(object : Callback<DeleteCommentResponse>{
            override fun onFailure(call: Call<DeleteCommentResponse>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<DeleteCommentResponse>?, response: Response<DeleteCommentResponse>?) {
                if(response!!.isSuccessful){
                    finish()
                }
            }
        })

    }
}
