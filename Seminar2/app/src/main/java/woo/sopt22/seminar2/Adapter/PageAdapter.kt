package woo.sopt22.seminar2.Adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import kotlinx.android.synthetic.main.fragment_home_viewpager_image.view.*
import woo.sopt22.seminar2.R


class PageAdapter : PagerAdapter {

    var arrayList: ArrayList<Int>? = null
    var inflater: LayoutInflater? = null

    constructor(inflater: LayoutInflater, arrayList: ArrayList<Int>) : super() {
        this.inflater = inflater
        this.arrayList = arrayList
    }


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as View
    }

    override fun getCount(): Int {
        return arrayList!!.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val v = inflater!!.inflate(R.layout.fragment_home_viewpager_image,container,false)

        var image : Int = (arrayList!!.get(position)) as Int

        v.viewpager_image.scaleType = ImageView.ScaleType.FIT_XY
        v.viewpager_image.setImageResource(image)
        container.addView(v)
        return v
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}

