package woo.sopt22.seminar4r.Get

data class GetCommentResponse (
        var message : String,
        var data : ArrayList<GetCommentResponseData>
)