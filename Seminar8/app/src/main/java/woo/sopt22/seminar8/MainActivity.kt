package woo.sopt22.seminar8

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_main.*
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var callbackManager: CallbackManager
    // 커스텀을 이용한 로그인 버튼 생성
    lateinit var lel : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        callbackManager = CallbackManager.Factory.create() // 콜백 매니저 생성


        main_fb_login_btn.setOnClickListener {
            loginWithFaceBook()
        }

        /*FIXME
        * 해쉬 키 얻는 코드
        * */
/*        try {
            val info = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }*/
    }


    // facebook로 로그인한다는 함수
    fun loginWithFaceBook(){

        LoginManager.getInstance().logInWithReadPermissions(this@MainActivity,
                Arrays.asList("public_profile", "email"))
        // profile과 email 정보를 가지고 오겠다.
        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            // 콜백을 등록하겠다.
            override fun onSuccess(loginResult: LoginResult) {
                val request: GraphRequest
                request = GraphRequest.newMeRequest(loginResult.accessToken) { user, response ->
                    if (response.error != null) {

                    } else {
                        Log.i("TAG", "user: " + user.toString())
                        Log.i("TAG", "AccessToken: " + loginResult.accessToken.token)
                        // 유효 토큰값
                        Log.i("TAG", "AccessToken: " + loginResult.accessToken.userId)


                        /*FIXME
                        * 토큰값이나 userId로 사용자를 구분하는데
                        * 토큰 보다는 userId로 사용자를 구분하는것이 좋다. 왜냐하면 토큰은 만료 기간이 있기 때문에(짧지는 않지만)
                        * 그래서 userId를 서버에 보내서 로그인하는 것이 좋다.
                        *
                        * 로그인을 성공해서 이 안으로 들어오면 받은 id(숫자로만 구성된것)를 서버에게 보낸다. (request)
                        * 그러면 로그인에 대한 response가 돌아올 것!
                        *
                        * 근데, accessToken을 보내면 이것은 수시로 바뀌기 때문에 이것을 서버에게 보내주게 되면 서버가 처음에 우리가 보낸 토큰을
                        * 디비에 저장하지만 accessToken이 변하게 되면 디비에 없는 경우가 발생하게 된다.
                        * 그래서 웬만하면 id를 보내는 방향으로 하는 것이 좋다.(서버와 말을 잘해서^^)
                        *
                        * */

                        setResult(Activity.RESULT_OK)

                    }
                }
                val parameters = Bundle()
                parameters.putString("fields", "id,name,email,gender,birthday")
                request.parameters = parameters
                request.executeAsync()
            }

            override fun onCancel() {

            }

            override fun onError(error: FacebookException) {

            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //super.onActivityResult(requestCode, resultCode, data)
        // 여기서는 메니페스트에 등록한 페이스북 로그인 액티비티를 부르고 여기서 필요한 정보들을 onActivityResult를 통해서 받아오게 된다.

        callbackManager.onActivityResult(requestCode, resultCode, data)

    }
}
