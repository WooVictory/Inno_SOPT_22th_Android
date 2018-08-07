package woo.sopt22.seminar4r.View

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_board.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import woo.sopt22.seminar4r.Network.ApplicationController
import woo.sopt22.seminar4r.Network.NetworkService
import woo.sopt22.seminar4r.Post.PostBoardResponse
import woo.sopt22.seminar4r.R
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream

class BoardActivity : AppCompatActivity(), View.OnClickListener {


    lateinit var networkService: NetworkService
    private val REQ_CODE_SELECT_IMAGE = 100
    private var image : MultipartBody.Part?=null
    lateinit var data : Uri

    override fun onClick(v: View?) {
        when(v!!){
            write_image_btn->{
                changeImage()
            }
            write_post_btn->{
                postBoard()
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board)

        write_image_btn.setOnClickListener(this)
        write_post_btn.setOnClickListener(this)
        networkService = ApplicationController.instance.networkService

    }

    fun postBoard(){
        /*FIXME
        * Post로 보낼 때 이미지나 파일이 존재하기 때문에 이미지나 파일은 MultiPart를 사용해서 보내게 되는데
        * 이럴 경우 서버로 보내는 인자값들을 Part라는 객체에 담아서 보내게 되는데 type이 RequestBody 타입이 된다.
        * 기존에는 String 형태이었기 때문에 TextView에서 가져와서 toString으로 문자열로 변환해서 넣어주었지만
        * Part라는 객체에 담겨서 보내지기 때문에 RequestBody 형태로 Type을 변환해주는 작업을 거쳐야 한다.
        * 이미지를 제외하고 String만!!!
        * */

        val title = RequestBody.create(MediaType.parse("text/plain"),write_title_tv.text.toString())
        val content = RequestBody.create(MediaType.parse("text/plain"),write_content_tv.text.toString())
        val writer = RequestBody.create(MediaType.parse("text/plain"),write_writer_tv.text.toString())
        val password = RequestBody.create(MediaType.parse("text/plain"),write_password_tv.text.toString())
        val postBoardResponse = networkService.postBoard(image,writer, title, content, password)
        postBoardResponse.enqueue(object : Callback<PostBoardResponse>{
            override fun onFailure(call: Call<PostBoardResponse>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<PostBoardResponse>?, response: Response<PostBoardResponse>?) {
                if(response!!.isSuccessful){
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    finish()
                }
            }

        })
    }

    /*FIXME
    * 이미지를 가져오기 위해서 갤러리에 접근하기 위해 정의된 함수
    * */
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

                    image = MultipartBody.Part.createFormData("photo",photo.name, photoBody)
                    // postman으로 확인했을 때 이미지의 key이 photo로 같아야 한다.

                    Glide.with(this).load(data.data).centerCrop().into(write_image)
                    // 내가 가지고 온 이미지를 이미지 뷰에 가운데를 중심으로 잘라서 Glide를 통해서 넣는다.

                } catch (e : Exception){
                    e.printStackTrace()
                }
            }
        }
    }
}
