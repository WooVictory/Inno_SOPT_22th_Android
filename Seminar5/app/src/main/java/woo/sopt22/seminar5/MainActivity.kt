package woo.sopt22.seminar5

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        // 이 액티비티가 실행되고 나서 이 레이아웃을 잡겠다는 말

        if(Build.VERSION.SDK_INT>=26){
            setContentView(R.layout.activity_main)
        }else{
            setContentView(R.layout.layout_main_25)

        }
    }
}
