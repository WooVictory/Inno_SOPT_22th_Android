package woo.sopt22.hapdongseminar.View.Login
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import woo.sopt22.hapdongseminar.MainActivity
import woo.sopt22.hapdongseminar.Model.UserInfo
import woo.sopt22.hapdongseminar.Network.ApplicationController
import woo.sopt22.hapdongseminar.Network.NetworkService
import woo.sopt22.hapdongseminar.R
import woo.sopt22.hapdongseminar.View.SignUp.SignUpActivity
import woo.sopt22.hapdongseminar.base.BaseModel
import woo.sopt22.hapdongseminar.base.utils.SharedPreferencesService

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            login_sign_btn->{
                startActivity(Intent(applicationContext, SignUpActivity::class.java))
            }
            login_ok_btn->{
                postLogin()
            }
        }
    }


    lateinit var networkService: NetworkService
    lateinit var userLoginInfo : UserInfo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        networkService = ApplicationController.instance.networkService

        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        // editText 밑줄 없애기
        login_id.background = null
        login_password.background = null

        login_sign_btn.setOnClickListener(this)
        login_ok_btn.setOnClickListener(this)

    }

    // user_id를 SharedPreference를 통해서 저장
    fun save(){
        SharedPreferencesService.instance!!.load(this)
        SharedPreferencesService.instance!!.setPrefData("user_id",login_id.text.toString())
    }


    // 로그인
    fun postLogin(){
        userLoginInfo = UserInfo(login_id.text.toString(), login_password.text.toString())

        val postLoginRequest = networkService.postLogin(userLoginInfo)
        postLoginRequest.enqueue(object : Callback<BaseModel>{
            override fun onFailure(call: Call<BaseModel>?, t: Throwable?) {
                Log.v("1717",t!!.message)
            }

            override fun onResponse(call: Call<BaseModel>?, response: Response<BaseModel>?) {
                if(response!!.isSuccessful){
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    save()

                }
            }

        })
    }
}
