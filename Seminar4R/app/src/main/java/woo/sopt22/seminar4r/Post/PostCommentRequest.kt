package woo.sopt22.seminar4r.Post

data class PostCommentRequest(
        var user_id : String,
        var board_idx : Int,
        var comment_content : String,
        var comment_pw : String
)