
# # SOPT 8차 세미나


## # 페이스북 연동 로그인
---

마지막 세미나인 8주차 세미나 시간에는 가장 많이 쓰이면서 간단한 방식인 **페이스북 연동 로그인**을 할 것입니다.
실제로 `AppJam`을 한다면 **SNS 연동 로그인** 기능을 많이 사용하기 때문에 페이스북 연동 로그인을 실습할 예정입니다.

> Part1. 페이스북 개발자 등록과 앱 ID를 얻기

**1.** 먼저, Google에 `페이스북 개발자`를 검색하고 두 번째 항목을 선택합니다.

![](SOPT/Seminar8/image/fb3.png)

**2.** 우측 상단의 `내 앱`을 클릭합니다.

**3.** `새 앱 추가` 버튼을 클릭합니다.

![](/image/fb.png)

**4.** 앱 ID를 작성하고 `앱 ID 만들기` 버튼을 클릭합니다.

![](/image/fb2.png)

**5.** 위의 단계를 거치게 되면 다음과 같은 화면이 나옵니다.

![](/image/fb4.png)

**6.** 여기서 `페이스북 로그인`에서 설정을 클릭합니다.

**7.** 아래의 화면에서 iOS, Android, WWW 중에서 사용할 것을 선택합니다. 안드로이드 개발을 위해서 사용하므로 안드로이드를 선택합니다.

![](/image/fb5.png) 

**8.** 1번은 다음을 선택해서 넘어갑니다.

![](/image/fb6.png)

**9.** 2번도 다음을 선택해서 넘어가도록 합니다.

![](/image/fb7.png)

**10.** 아래의 패키지 이름에 진행하고 있는 프로젝트의 Manifest 파일에 가서 패키지 이름을 복사한 다음 붙여넣기를 합니다. 기본 액티비티 클래스 이름은 작성하지 않아도 됩니다.

![](/image/fb8.png)

**11.** 그 다음에 좌측에 설정 - 기본 설정으로 들어간 다음에 하단의 플랫폼 추가를 클릭하여 패키지 이름과 해쉬 키 내용을 작성하고 저정을 누릅니다. 해쉬 키를 얻는 과정은 아래에서 설명하도록 하겠습니다.

![](/image/fb9.png)

**12.** 그리고 상단의 보이는 앱 ID를 복사한 다음 안드로이드 스튜디오에서 진행하는 프로젝트로 이동합니다.


> Part2. 해쉬 키를 얻고 페이스북 로그인을 진행해보자.

1. 먼저, 해쉬 키를 얻기 위해서 코드를 작성합니다. 아래의 코드는 세미나 시간에 파트장님께서 공유해주신 코드로 이 코드를 통해서 고유의 해쉬 키를 얻습니다. (참고로 지금 사용하는 해쉬 키는 나중에 배포할 때 사용하는 릴리즈용 해쉬 키와는 다릅니다. 따라서 배포를 위해서는 다시 릴리즈용 해쉬 키를 발급 받아서 사용해야 합니다. 지금은 배포의 목적이 아니기 때문에 이 해쉬 키를 사용합니다.) 이를 통해서 얻은 해쉬 키를 위의 과정에서 `11번`에 등록합니다.

```java
        try {
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
        }
```

2. gradle의 Project 안에다가 `mavenCentral()`를 추가합니다.

![](/image/fbb1.png)

**3.** 그 다음은 gradle의 app 안에 dependencies에 아래의 코드를 추가합니다.

```
implementation 'com.facebook.android:facebook-login:[4,5)'
```

4. 그리고 **Manifest** 파일에 페이스북 로그인을 위해서 **인터넷 접근**을 허용해줍니다.

![](/image/fbb2.png)

5. 다음으로는 res - valuse - string.xml 파일에 **Part1**에서 얻은 앱 ID를 가져와서 등록합니다. (나의 앱 ID가 노출되기 때문에 가상의 앱 ID인 000000000000000를 사용하였습니다.)

![](/image/fbb3.png)

6. 다음 과정은 Manifest의 application 태그 안에다가 `provider, meta-data, activity`를 추가해줍니다.

![](/image/fbb4.png)

7. MainActivity에 버튼을 하나 추가하고 다음 코드를 작성한 뒤 실행을 하면 페이스북 로그인이 되는 것을 확인할 수 있습니다. 그리고 Log를 찍어 확인해보면 사용자의 정보를 페이스북으로부터 받아온 것을 볼 수 있습니다.


```
class MainActivity : AppCompatActivity() {

    lateinit var callbackManager: CallbackManager
    // 커스텀을 이용한 로그인 버튼 생성
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        callbackManager = CallbackManager.Factory.create() // 콜백 매니저 생성


        main_fb_login_btn.setOnClickListener {
            loginWithFaceBook()
        }

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

```

