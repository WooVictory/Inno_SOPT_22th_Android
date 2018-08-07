package woo.sopt22.seminar2

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.AttributeSet
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import woo.sopt22.seminar2.MineTab.MineTab

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            main_home_btn->{
                clearSelected()
                main_home_btn.isSelected = true
                replaceFragment(HomeTab())
            }
            main_mine_btn->{
                clearSelected()
                main_mine_btn.isSelected = true
                replaceFragment(MineTab())

            }
            main_add_btn->{
                val intent = Intent(applicationContext,AddActivity::class.java)
                intent.putExtra("add_image",R.drawable.add_image)
                startActivity(intent)
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragment(HomeTab())
        main_home_btn.isSelected = true
        main_home_btn.setOnClickListener(this)
        main_mine_btn.setOnClickListener(this)
        main_add_btn.setOnClickListener(this)
    }


    fun addFragment(fragment : Fragment){
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.add(R.id.main_frame, fragment)
        transaction.commit()
    }

    fun replaceFragment(fragment : Fragment){
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.replace(R.id.main_frame, fragment)
        transaction.commit()
    }
    fun clearSelected(){
        main_home_btn.isSelected = false
        main_mine_btn.isSelected = false
    }



}
