package woo.sopt22.seminar1.Seminar2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add.*
import woo.sopt22.seminar1.R

class AddActivity : AppCompatActivity() {

    var image : Int = 0 // 변수 : var 상수 : val 타입 명시하지 않아도 된다. 대신에 타입을 명시하면 미리 메모리에 공간을 얼마나 할당해야 하는지 알 수 있다.
    /*Java
    * int name = 0;과 같음
    * */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        image = intent.getIntExtra("add_image",0)
        // 이미지를 주지 않았을 떄, 혹은 이미지가 없을 떄 기본 [default]값으로 받아오기 위해서 0을 넣어준다.
        add_image.setImageResource(image)


    }
}
