package woo.sopt22.hapdongseminar.Model

import com.google.gson.annotations.SerializedName
import woo.sopt22.hapdongseminar.base.BaseModel

data class StoreListResponse(
        // 가게 리스트
        @SerializedName("result")
        var result : ArrayList<StoreListResponseData>

) : BaseModel()