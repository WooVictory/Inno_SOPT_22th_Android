package woo.sopt22.hapdongseminar.Network

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*
import woo.sopt22.hapdongseminar.Model.*
import woo.sopt22.hapdongseminar.base.BaseModel

interface NetworkService {


    // 1. 회원가입 - 0
    @POST("signup")
    fun postSignUp(
            @Body userSignInfo : UserInfo
    ) : Call<BaseModel>

    // 2. 로그인 - 0
    @POST("signin")
    fun postLogin(
            @Body userLoginInfo : UserInfo
    ) : Call<BaseModel>

    // 3. 카테고리에 맞게 불러오기 - 0
    @POST("store/list")
    fun postStoreList(
            @Body storeCategory: StoreCategory
    ) : Call<StoreListResponse>

    // 4. 가게 등록하기 - 0
    @Multipart
    @POST("store/register")
    fun postStoreRegister(
            @Part ("user_id") user_id : RequestBody,
            @Part ("store_name") store_name : RequestBody,
            @Part ("store_category") store_category : RequestBody,
            @Part store_photo : MultipartBody.Part?,
            @Part ("store_content") store_content : RequestBody,
            @Part ("menu_list[0][menu_name]") menu_name : RequestBody,
            @Part ("menu_list[0][menu_price]") menu_price : RequestBody
    ) : Call<BaseModel>

    // 5. 북마크 등록하기 - 0
    @POST("bookmark/like")
    fun postBookmark(
            @Body bookmarkRegister: BookmarkRegister
    ) : Call<BaseModel>

    // 6. 북마크 삭제하기 - 0
    @HTTP(method = "DELETE", path = "bookmark/like", hasBody = true)
    fun deleteBookmark(
            @Body bookmarkRegister: BookmarkRegister
    ):   Call<BaseModel>

    // 7. (개인별)북마크 조회하기 - 0
    @GET("bookmark/list/{user_id}")
    fun getBookmark(
            @Path ("user_id") user_id : String
    ) : Call<StoreListResponse>

    // 8. 가게 페이지 '매뉴'보기 - 0
    @GET("store/menu/{store_idx}")
    fun getStoreMenu(
            @Path ("store_idx") store_idx : Int
    ) : Call<StoreMenu>

    // 9. 가게 페이지 '정보' 보기 - 0
    @GET("store/info/{store_idx}")
    fun getStoreInfo(
            @Path ("store_idx") store_idx: Int
    ) : Call<StoreInfo>

    // 10. 가게 페이지 '리뷰'보기 - 0
    @GET("store/review/{store_idx}")
    fun getStoreReview(
            @Path ("store_idx") store_idx: Int
    ) : Call<StoreReview>

    // 11. 리뷰 등록하기 - 0
    @Multipart
    @POST("store/review")
    fun postReviewRegister(
            @Part ("store_idx") store_idx: Int,
            @Part ("user_id") user_id: RequestBody,
            @Part ("review_content") review_content : RequestBody,
            @Part review_photo : MultipartBody.Part?
    ) : Call<BaseModel>

}