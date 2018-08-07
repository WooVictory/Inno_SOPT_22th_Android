package woo.sopt22.seminar2

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import woo.sopt22.seminar2.Adapter.PageAdapter

class HomeTab : Fragment() {

    var arrayList = ArrayList<Int>()
    var pageAdapter : PageAdapter?=null
    var al = ArrayList<String>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_home,container,false)
        v.fragment_home_viewpager
        arrayList!!.add(R.drawable.adidas)
        arrayList!!.add(R.drawable.nike)


        pageAdapter = PageAdapter(layoutInflater, arrayList!!)

        v.fragment_home_viewpager.adapter = pageAdapter
        return v
    }


}