package woo.sopt22.hapdongseminar.Model

data class StoreReviewRegister(
        // 리뷰 등록하기
        var store_idx : Int,
        var user_id : String,
        var review_content : String, // 나중에 철자 바뀔 것임
        var review_photo : String
)
