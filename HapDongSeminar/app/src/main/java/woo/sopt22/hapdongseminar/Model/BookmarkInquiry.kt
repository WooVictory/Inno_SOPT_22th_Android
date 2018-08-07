package woo.sopt22.hapdongseminar.Model

import com.google.gson.annotations.SerializedName
import woo.sopt22.hapdongseminar.base.BaseModel

data class BookmarkInquiry (
        // 북마크 조회하기
        @SerializedName("result")
        var result : ArrayList<BookmarkInquiryData>
) : BaseModel()