package woo.sopt22.seminar3.Review1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_profile.*
import woo.sopt22.seminar3.R

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        profile_imageView.setImageResource(intent.getIntExtra("profileImage",0))
        profile_chattingName.text = intent.getStringExtra("chattingName")

    }
}
