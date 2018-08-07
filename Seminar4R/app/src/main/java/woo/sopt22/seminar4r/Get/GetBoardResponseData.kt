package woo.sopt22.seminar4r.Get


data class GetBoardResponseData(

        // 데이터 클래스 작성
        // 이것이 한 객체 형태로 묶인다.
        var board_idx : Int,
        var board_title : String?,
        var board_content : String,
        var board_view : Int,
        var board_photo : String?,
        var board_writetime : String?,
        var board_pw : String,
        var user_id : String
)
