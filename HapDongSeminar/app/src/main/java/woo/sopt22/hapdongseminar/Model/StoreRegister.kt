package woo.sopt22.hapdongseminar.Model

import okhttp3.RequestBody

data class StoreRegister(
        // 가게 등록
        var store_name : String,
        var store_category : String,
        var store_photo : String,
        var store_content: String,
        var menu_list : ArrayList<StoreRegisterData>
)