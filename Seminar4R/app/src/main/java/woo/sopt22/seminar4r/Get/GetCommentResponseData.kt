package woo.sopt22.seminar4r.Get

data class GetCommentResponseData (
      var comment_idx : Int,
      var comment_content : String?,
      var comment_writetime : String?,
      var user_id : String,
      var board_idx : Int

)