package woo.sopt22.hapdongseminar.Model

import com.google.gson.annotations.SerializedName
import woo.sopt22.hapdongseminar.base.BaseModel

data class StoreInfo(
        // 가게 페이지 '정보' 보기
        @SerializedName("result")
        var result : ArrayList<StoreInfoData>
) : BaseModel()