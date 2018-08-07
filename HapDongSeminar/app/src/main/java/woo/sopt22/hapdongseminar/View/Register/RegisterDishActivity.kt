package woo.sopt22.hapdongseminar.View.Register

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.IntegerRes
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_register_dish.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import woo.sopt22.hapdongseminar.Model.StoreMenuData
import woo.sopt22.hapdongseminar.Model.StoreRegister
import woo.sopt22.hapdongseminar.Model.StoreRegisterData
import woo.sopt22.hapdongseminar.Network.ApplicationController
import woo.sopt22.hapdongseminar.Network.NetworkService
import woo.sopt22.hapdongseminar.R
import woo.sopt22.hapdongseminar.View.Detail.DishListActivity
import woo.sopt22.hapdongseminar.base.BaseModel
import woo.sopt22.hapdongseminar.base.utils.SharedPreferencesService
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream

class RegisterDishActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            register_dish_back_btn->{
                finish()
            }
            registerPhoto->{
                changeImage()
            }
            registerBtn->{
                registerStore()
            }
            register_dish_back_btn->{
                startActivity(Intent(applicationContext, DishListActivity::class.java))
            }
        }
    }


    lateinit var networkService: NetworkService
    lateinit var storeRegister: StoreRegister
    lateinit var storeRegisterData: StoreRegisterData
    private val REQ_CODE_SELECT_IMAGE = 100
    private var image : MultipartBody.Part?=null
    lateinit var data : Uri
    //lateinit var menuList : ArrayList<JSONObject>
    lateinit var menuList : JSONArray
    lateinit var jsonObject: JSONObject

    /*JSONObject obj = new JSONObject();

    obj.put("menu_name",registerMenuName.text.toString());
    obj.put("menu_price","서울");*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_dish)

        networkService = ApplicationController.instance.networkService
        SharedPreferencesService.instance!!.load(this)
        Log.v("01022",SharedPreferencesService.instance!!.getPrefStringData("user_id"))

        registerPhoto.setOnClickListener(this)
        registerBtn.setOnClickListener(this)
        register_dish_back_btn.setOnClickListener(this)

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

                    image = MultipartBody.Part.createFormData("store_photo",photo.name, photoBody)
                    // postman으로 확인했을 때 이미지의 key이 photo로 같아야 한다.

                    Glide.with(this).load(data.data).centerCrop().into(registerPhoto)
                    // 내가 가지고 온 이미지를 이미지 뷰에 가운데를 중심으로 잘라서 Glide를 통해서 넣는다.
                    registerTv.text=""


                } catch (e : Exception){
                    e.printStackTrace()
                }
            }
        }
    }

    fun registerStore(){

        var registerCategoryNum : String?=null
        when(registerCategory.text.toString()){
            "한식"->{
                registerCategoryNum = "101"
            }
            "치킨"->{
                registerCategoryNum = "102"
            }
            "피자"->{
                registerCategoryNum = "103"
            }
            "야식"->{
                registerCategoryNum = "104"
            }
        }


        val userId = RequestBody.create(MediaType.parse("multipart/form-data"), SharedPreferencesService.instance!!.getPrefStringData("user_id"))
        val registerStoreName = RequestBody.create(MediaType.parse("multipart/form-data"), registerStoreName.text.toString())
        val registerStoreExplain = RequestBody.create(MediaType.parse("multipart/form-data"), registerStoreExplain.text.toString())
        val registerCategory = RequestBody.create(MediaType.parse("multipart/form-data"), registerCategoryNum!!)
        val menu_name = RequestBody.create(MediaType.parse("multipart/form-data"),registerMenuName.text.toString())
        val menu_price = RequestBody.create(MediaType.parse("multipart/form-data"),registerMenuPrice.text.toString())

        //var menuList : ArrayList<RequestBody> = arrayListOf(menu_name, menu_price)

        menuList = JSONArray()

        //menuList!!.add(StoreRegisterData(registerMenuName.text.toString(),registerMenuPrice.text.toString()))
        jsonObject = JSONObject()
        jsonObject.put("menu_name",registerMenuName.text.toString())
        jsonObject.put("menu_price",registerMenuPrice.text.toString())
        //menuList.add(jsonObject)
        menuList.put(jsonObject)
        Log.v("99",jsonObject.toString())
        Log.v("98",menuList.toString())

        //storeRegisterData = StoreRegisterData(registerMenuName.text.toString(), registerMenuPrice.text.toString())
        //var menuList : ArrayList<StoreRegisterData> = arrayListOf(storeRegisterData)

        val postRegisterStore = networkService.postStoreRegister(userId!!,registerStoreName ,registerCategory,image,registerStoreExplain,menu_name,menu_price)
        Log.v("78",registerCategoryNum.toString())
        Log.v("77",registerMenuPrice.text.toString())
        Log.v("79",menuList.toString())
        Log.v("01022",SharedPreferencesService.instance!!.getPrefStringData("user_id"))
        postRegisterStore.enqueue(object : Callback<BaseModel>{
            override fun onFailure(call: Call<BaseModel>?, t: Throwable?) {
                //Log.v("44",t!!.message)
                Log.v("01023",SharedPreferencesService.instance!!.getPrefStringData("user_id"))

            }

            override fun onResponse(call: Call<BaseModel>?, response: Response<BaseModel>?) {
                if(response!!.isSuccessful){
                    Log.v("01024",SharedPreferencesService.instance!!.getPrefStringData("user_id"))
                    Log.v("55",response!!.body().message)
                    Log.v("55",menuList.get(0).toString())
                    finish()
                }
            }

        })


    }
}
