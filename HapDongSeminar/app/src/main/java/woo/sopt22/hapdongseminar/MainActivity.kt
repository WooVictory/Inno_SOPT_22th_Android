package woo.sopt22.hapdongseminar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import woo.sopt22.hapdongseminar.View.Bookmark.BookMarkFragment
import woo.sopt22.hapdongseminar.View.Home.HomeFragment

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            main_home_btn->{
                // 홈 버튼
                replaceFragment(HomeFragment())
                main_toolbar_tv.text = "메인"

            }
            main_bookmark_btn->{
                // 북마크 버튼
                replaceFragment(BookMarkFragment())
                main_toolbar_tv.text = "북마크"
            }
       }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragment(HomeFragment())
        main_home_btn.setOnClickListener(this)
        main_bookmark_btn.setOnClickListener(this)
    }

    fun addFragment(fragment : Fragment){
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.add(R.id.main_container,fragment)
        transaction.commit()
    }

    fun replaceFragment(fragment : Fragment){
        val fm = supportFragmentManager
        val transaction  = fm.beginTransaction()
        transaction.replace(R.id.main_container, fragment)
        transaction.commit()
    }
}
