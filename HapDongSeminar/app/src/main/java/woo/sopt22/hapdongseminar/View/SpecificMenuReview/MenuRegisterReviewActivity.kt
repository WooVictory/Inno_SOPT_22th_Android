package woo.sopt22.hapdongseminar.View.SpecificMenuReview

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Network
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_menu_register_review.*
import kotlinx.android.synthetic.main.activity_register_dish.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import woo.sopt22.hapdongseminar.Model.StoreReviewData
import woo.sopt22.hapdongseminar.Model.StoreReviewRegister
import woo.sopt22.hapdongseminar.Network.ApplicationController
import woo.sopt22.hapdongseminar.Network.NetworkService
import woo.sopt22.hapdongseminar.R
import woo.sopt22.hapdongseminar.base.BaseModel
import woo.sopt22.hapdongseminar.base.utils.SharedPreferencesService
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream

class MenuRegisterReviewActivity : AppCompatActivity(),View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            register_review_image_attach_btn->{ // 사진 첨부 버튼
                changeImage()
            }
            register_review_okay_btn->{
                postReviewRegister()
            }
        }
    }

    private val REQ_CODE_SELECT_IMAGE = 100
    private var image : MultipartBody.Part?=null
    lateinit var data : Uri
    lateinit var storeReviewRegister: StoreReviewRegister
    lateinit var networkService: NetworkService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_register_review)

        SharedPreferencesService.instance!!.load(this)
        networkService = ApplicationController.instance.networkService



        register_review_image_attach_btn.setOnClickListener(this)
        register_review_okay_btn.setOnClickListener(this)


    }

    fun postReviewRegister(){
        //val storeIdx = RequestBody.create(MediaType.parse("multipart/form-data"), SharedPreferencesService.instance!!.getPrefIntegerData("store_idx",0).toString())
        val userId = RequestBody.create(MediaType.parse("multipart/form-data"), SharedPreferencesService.instance!!.getPrefStringData("user_id"))
        val reviewContent = RequestBody.create(MediaType.parse("multipart/form-data"), register_review_content.text.toString())

        val postStoreReview = networkService.postReviewRegister(
                SharedPreferencesService.instance!!.getPrefIntegerData("store_idx",0)
                ,userId
                ,reviewContent
                ,image)

        postStoreReview.enqueue(object : Callback<BaseModel>{
            override fun onFailure(call: Call<BaseModel>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<BaseModel>?, response: Response<BaseModel>?) {
                if(response!!.isSuccessful){
                    Log.v("120",response!!.body().message)
                    finish()
                }
            }
        })


    }


    fun changeImage(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
        intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        startActivityForResult(intent, REQ_CODE_SELECT_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQ_CODE_SELECT_IMAGE){
            if(resultCode == Activity.RESULT_OK){
                try{
                    this.data = data!!.data
                    Log.v("이미지",this.data.toString())

                    val options = BitmapFactory.Options()

                    var input : InputStream?=null
                    try{
                        input = contentResolver.openInputStream(this.data)
                    } catch (e : FileNotFoundException){
                        e.printStackTrace()
                    }

                    // 여기까지 적당한 형태로 변환하고

                    val bitmap = BitmapFactory.decodeStream(input, null,options)
                    val baos = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos)
                    val photoBody = RequestBody.create(MediaType.parse("image/jpg"),baos.toByteArray())
                    val photo = File(this.data.toString()) // 파일의 이름을 알아내려고 한다.

                    image = MultipartBody.Part.createFormData("review_photo",photo.name, photoBody)
                    // postman으로 확인했을 때 이미지의 key이 photo로 같아야 한다.

                    Glide.with(this).load(data.data).centerCrop().into(register_review_image)
                    // 내가 가지고 온 이미지를 이미지 뷰에 가운데를 중심으로 잘라서 Glide를 통해서 넣는다.


                } catch (e : Exception){
                    e.printStackTrace()
                }
            }
        }
    }
}
