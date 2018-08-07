package woo.sopt22.hapdongseminar.Model

data class StoreListResponseData(
        var store_idx : Int,
        var store_name : String,
        var store_category : String,
        var store_photo : String,
        var store_content : String,
        var user_idx : Int,
        var bookmarkCheck : Boolean
)
