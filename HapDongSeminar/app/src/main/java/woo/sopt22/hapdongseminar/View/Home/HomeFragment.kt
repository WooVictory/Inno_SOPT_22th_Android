package woo.sopt22.hapdongseminar.View.Home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import woo.sopt22.hapdongseminar.R
import woo.sopt22.hapdongseminar.View.Detail.DishListActivity
import woo.sopt22.hapdongseminar.base.utils.SharedPreferencesService


class HomeFragment : Fragment(), View.OnClickListener {

    private val HANSIK_NUM : String = "101"
    private val CHICKEN_NUM : String = "102"
    private val PIZZA_NUM : String = "103"
    private val YASIK_NUM : String = "104"



    override fun onClick(v: View?) {
        when(v!!){
            hansik_btn->{ // 한식
                val intent = Intent(context, DishListActivity::class.java)
                intent.putExtra("category_name","한식")
                intent.putExtra("result",HANSIK_NUM)
                startActivity(intent)

            }
            pizza_btn->{ // 피자
                val intent = Intent(context, DishListActivity::class.java)
                intent.putExtra("category_name","피자")
                intent.putExtra("result",PIZZA_NUM)
                startActivity(intent)
            }
            chicken_btn->{ // 치킨
                val intent = Intent(context, DishListActivity::class.java)
                intent.putExtra("category_name","치킨")
                intent.putExtra("result",CHICKEN_NUM)
                startActivity(intent)
            }
            yasik_btn->{ // 야식
                val intent = Intent(context, DishListActivity::class.java)
                intent.putExtra("category_name","야식")
                intent.putExtra("result",YASIK_NUM)
                startActivity(intent)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        view.hansik_btn.setOnClickListener(this)
        view.pizza_btn.setOnClickListener(this)
        view.chicken_btn.setOnClickListener(this)
        Glide.with(context).load(R.drawable.pizza).into(view.pizza_btn)
        Glide.with(context).load(R.drawable.hansik).into(view.hansik_btn)
        Glide.with(context).load(R.drawable.chicken).into(view.chicken_btn)
        Glide.with(context).load(R.drawable.jokbal).into(view.yasik_btn)

        view.yasik_btn.setOnClickListener(this)
        return view
    }

}