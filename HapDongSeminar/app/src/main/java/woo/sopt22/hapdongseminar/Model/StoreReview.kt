package woo.sopt22.hapdongseminar.Model

import com.google.gson.annotations.SerializedName
import woo.sopt22.hapdongseminar.base.BaseModel

data class StoreReview(
        // 가게 페이지 '리뷰'보기
        @SerializedName("result")
        var result : ArrayList<StoreReviewData>
) : BaseModel()