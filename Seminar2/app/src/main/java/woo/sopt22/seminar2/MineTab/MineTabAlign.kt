package woo.sopt22.seminar2.MineTab

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import woo.sopt22.seminar2.R

class MineTabAlign : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.mine_fragment_align,container,false)
        return v
    }
}