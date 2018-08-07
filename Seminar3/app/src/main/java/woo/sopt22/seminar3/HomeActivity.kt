package woo.sopt22.seminar3

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_home.*
import woo.sopt22.seminar3.Review1.Review1Activity

class HomeActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            three_seminar_review1_btn->{
                startActivity(Intent(applicationContext, Review1Activity::class.java))
            }
            three_seminar_review2_btn->{
                startActivity(Intent(applicationContext, MainActivity::class.java))

            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        three_seminar_review1_btn.setOnClickListener(this)
        three_seminar_review2_btn.setOnClickListener(this)


    }
}
