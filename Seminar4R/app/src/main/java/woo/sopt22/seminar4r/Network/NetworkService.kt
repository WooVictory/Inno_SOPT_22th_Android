package woo.sopt22.seminar4r.Network

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import woo.sopt22.seminar4r.Delete.DeleteBoardRequest
import woo.sopt22.seminar4r.Delete.DeleteBoardResponse
import woo.sopt22.seminar4r.Get.GetBoardResponse
import woo.sopt22.seminar4r.Get.GetCommentResponse
import woo.sopt22.seminar4r.Post.PostBoardResponse
import woo.sopt22.seminar4r.Post.PostCommentRequest
import woo.sopt22.seminar4r.Post.PostCommentResponse
import retrofit2.http.HTTP
import woo.sopt22.seminar4r.Delete.DeleteCommentRequest
import woo.sopt22.seminar4r.Delete.DeleteCommentResponse


interface NetworkService {

    // 통신에 사용할 메소드를 정의만 해놓는다.

    // 1. 게시판 글 조회
    @GET("board")
    fun getContent() : Call<GetBoardResponse>

    // 2. 게시판 글 등록
    @Multipart
    @POST("board")
    fun postBoard(
            @Part boardImage : MultipartBody.Part?,
            @Part ("user_id") id : RequestBody,
            @Part ("board_title") title : RequestBody,
            @Part ("board_content") content : RequestBody,
            @Part ("board_pw") pw : RequestBody
    ) : Call<PostBoardResponse>

    // 3. 댓글 보기
    @GET("comment/{board_idx}")
    fun getComment(
            @Path ("board_idx") board_idx : Int
    ) : Call<GetCommentResponse>

    // 4. 댓글등록
    @POST("comment")
    fun postComment(
            @Body postCommentRequest: PostCommentRequest
    ) : Call<PostCommentResponse>

    // 5. 게시판 글 삭제
    @HTTP(method = "DELETE", path = "board", hasBody = true)
    fun deleteBoard(
            @Body deleteBoardRequest: DeleteBoardRequest
    ):   Call<DeleteBoardResponse>

    // 6. 댓글 삭제
    @HTTP(method = "DELETE", path = "comment", hasBody = true)
    fun deleteComment(
            @Body deleteCommentRequest: DeleteCommentRequest
    ) : Call<DeleteCommentResponse>

}