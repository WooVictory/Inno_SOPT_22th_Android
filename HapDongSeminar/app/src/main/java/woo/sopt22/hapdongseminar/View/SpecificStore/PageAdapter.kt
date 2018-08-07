package woo.sopt22.hapdongseminar.View.SpecificStore

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import woo.sopt22.hapdongseminar.View.SpecificMenuReview.FragmentReview

class PageAdapter(fm : FragmentManager, var tabCount : Int) : FragmentStatePagerAdapter(fm){

    override fun getItem(position: Int): Fragment? {
        when(position) {
            0 -> {
                val fragmentMenu = FragmentMenu()
                return fragmentMenu
            }
            1 -> {
                val fragmentInformation = FragmentInformation()
                return fragmentInformation
            }
            2 -> {
                val fragmentReview = FragmentReview()
                return fragmentReview
            }
            else->{
                return null
            }
        }
    }

    override fun getCount(): Int {
        return tabCount
    }


}

