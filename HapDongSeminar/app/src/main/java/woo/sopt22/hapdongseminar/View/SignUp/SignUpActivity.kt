package woo.sopt22.hapdongseminar.View.SignUp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_sign_up.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import woo.sopt22.hapdongseminar.Model.UserInfo
import woo.sopt22.hapdongseminar.Network.ApplicationController
import woo.sopt22.hapdongseminar.Network.NetworkService
import woo.sopt22.hapdongseminar.R
import woo.sopt22.hapdongseminar.base.BaseModel
import woo.sopt22.hapdongseminar.base.utils.ToaskMaker

class SignUpActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            sign_ok_btn->{
                postSignUp()
            }
        }
    }

    lateinit var networkService: NetworkService
    lateinit var userSignInfo: UserInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // EditText 밑줄 없애기
        sign_id.background = null
        sign_password.background = null

        sign_ok_btn.setOnClickListener(this)

        networkService = ApplicationController.instance.networkService

    }

    // 회원 가입
    fun postSignUp(){
        userSignInfo = UserInfo(sign_id.text.toString(), sign_password.text.toString())
        val postSignRequest = networkService.postSignUp(userSignInfo)
        postSignRequest.enqueue(object : Callback<BaseModel>{
            override fun onFailure(call: Call<BaseModel>?, t: Throwable?) {
                Log.v("1818",t!!.message)

            }

            override fun onResponse(call: Call<BaseModel>?, response: Response<BaseModel>?) {
                if(response!!.isSuccessful){
                    ToaskMaker.makeShortToast(this@SignUpActivity
                            ,response!!.body().message.toString())
                    finish()
                }
            }
        })

    }
}
