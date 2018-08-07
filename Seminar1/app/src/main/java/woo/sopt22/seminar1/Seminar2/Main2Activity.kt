package woo.sopt22.seminar1.Seminar2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import kotlinx.android.synthetic.main.activity_main2.*
import woo.sopt22.seminar1.R

class Main2Activity : AppCompatActivity(), View.OnClickListener{
    override fun onClick(v: View?) {
        when(v!!){
            main2_home_btn->{
                clearSelected()
                main2_home_btn.isSelected = true
                replaceFragment(HomeTab())
            }
            main2_mine_btn->{
                clearSelected()
                main2_mine_btn.isSelected = true
                replaceFragment(MineTab())
            }
            main2_add_btn->{
                val intent = Intent(applicationContext, AddActivity::class.java) // 자바와 코틀린간의 호환성 (섞어서 쓰기 위해서) 이렇게 사용
                // 현재 내가 보고 있는 화면이나 액티비티나 현재 여기 위에 띄워져있는 화면이 무엇이냐가 context를 의미한다.
                intent.putExtra("add_image", R.drawable.add_image)
                startActivity(intent)
            }
        }
        /*
        when : break가 따로 필요 없다.
        * */
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        /*
        Fragment frgment;
        frgment = new HomeTab()
        addFragment(fragment);
        */
        addFragment(HomeTab())
        // 코틀린에서는 new라는 키워드가 없기 때문에 HomeTab() 생성자를 넣어주면 바로 객체화가 된다.

        main2_home_btn.isSelected = true

/*        main2_home_btn.setOnClickListener {
            replaceFragment(HomeTab())
            // 다른 곳에 있다가 main2_home_btn을 눌렀을 때 home_tab으로 돌아온다는 뜻!
        }*/
/*        main2_mine_btn.setOnClickListener {
            replaceFragment(MineTab())

        }*/

        main2_home_btn.setOnClickListener(this)
        // 이렇게 하면 위에서 상속한 onClick()함수를 이 버튼에 넣어줌으로써 사용할 수 있다.
        main2_mine_btn.setOnClickListener(this)
        main2_add_btn.setOnClickListener(this)

    }

    /*
    * fun : 함수 키워드
    * 변수 이름이 먼저오고 그 다음에 type이 온다.
    * Unit : 리턴 타입이 없는 void와 똑같음. 이 경우 생략 가능
    * val : 상수
    * Fragment를 붙이기 위해서 supportFragmentManager[fm]을 사용한다.
    * fm.beginTransaction
    * 이것을 통해서 Fragment를 add 할 것!
    * main_frame에 fragment를 붙인다.
    * */
    fun addFragment(fragment : Fragment) : Unit{
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.add(R.id.main2_frame,fragment)
        transaction.commit()
    }

    // add를 써도 상관은 없음

    fun replaceFragment(fragment: Fragment){
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.replace(R.id.main2_frame,fragment)
        // 원래 fragment는 stack에 쌓이는 자료구조가 아니다.
        transaction.addToBackStack(null) // 괄호에는 그냥 null을 넣어주면 된다.
        transaction.commit()
    }

    fun clearSelected(){
        main2_home_btn.isSelected = false
        //btn_main_search.isSelected = false
        //btn_main_news.isSelected = false
        main2_mine_btn.isSelected = false
    }
}
