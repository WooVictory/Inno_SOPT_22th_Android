package woo.sopt22.seminar4r.View

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign_up.*
import woo.sopt22.seminar4r.R
import woo.sopt22.seminar4r.SharedPreferencesService

class SignUpActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.sign_okay_btn->{
                SharedPreferencesService.instance!!.load(this)
                user_id = sign_id.text.toString()
                user_name = sign_name.text.toString()
                user_password = sign_password.text.toString()
                SharedPreferencesService.instance!!.setPrefData("user_id",user_id!!)
                SharedPreferencesService.instance!!.setPrefData("user_name",user_name!!)
                SharedPreferencesService.instance!!.setPrefData("user_password",user_password)
                Log.v("000",SharedPreferencesService.instance!!.getPrefStringData("user_id"))

                if(sign_password.text.toString().equals(sign_passwordOneMore.text.toString())){
                    user_password = sign_password.text.toString()
                } else{
                    Toast.makeText(applicationContext, "비밀번호를 똑바로 입력하세요.",Toast.LENGTH_LONG).show()
                }

                startActivity(Intent(application, LoginActivity::class.java))

            }
            R.id.sign_cancel_btn->{

            }
        }
    }

    lateinit var user_id : String
    lateinit var user_name : String
    lateinit var user_password : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        sign_okay_btn.setOnClickListener(this)
        sign_cancel_btn.setOnClickListener(this)




    }
}
