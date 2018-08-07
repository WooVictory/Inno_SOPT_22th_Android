package woo.sopt22.seminar2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {

    var image : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        image = intent.getIntExtra("add_image",0)
        add_image.setImageResource(image)
        add_image.scaleType = ImageView.ScaleType.FIT_XY


    }

}
