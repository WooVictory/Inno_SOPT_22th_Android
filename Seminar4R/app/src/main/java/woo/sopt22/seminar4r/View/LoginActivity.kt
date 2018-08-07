package woo.sopt22.seminar4r.View

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import woo.sopt22.seminar4r.R
import woo.sopt22.seminar4r.SharedPreferencesService

class LoginActivity : AppCompatActivity(), View.OnClickListener{
    override fun onClick(v: View?) {
        when(v!!.id)
        {
            R.id.login_btn->{
                sign()

            }
            R.id.login_sign_btn->{
                startActivity(Intent(applicationContext, SignUpActivity::class.java))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        SharedPreferencesService.instance!!.load(this)
        login_btn.setOnClickListener(this)
        login_sign_btn.setOnClickListener(this)
    }

    fun sign()
    {

        if(SharedPreferencesService.instance!!
                        .getPrefStringData("user_id").equals("") or
                SharedPreferencesService.instance!!
                        .getPrefStringData("user_password").equals("")){
            toast("회원 정보가 없습니다.")
        }



        if(login_id.text.toString().equals("")){
            toast("아이디를 입력해주세요.")
        }else if(login_password.text.toString().equals("")){
            toast("비밀번호를 입력해주세요.")
        }else if(login_id.text.toString().equals("") and login_password.text.toString().equals("")){
            toast("아이디 혹은 비밀번호를 입력해주세요.")
        }


        if(login_id.text.toString().equals(SharedPreferencesService.instance!!
                        .getPrefStringData("user_id"))
            and login_password.text.toString().equals(SharedPreferencesService.instance!!
                        .getPrefStringData("user_password"))){
            Log.v("77",SharedPreferencesService.instance!!.getPrefStringData("user_id"))
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }


    }
    fun toast(str : String){
        Toast.makeText(applicationContext, str, Toast.LENGTH_LONG).show()
    }
}
