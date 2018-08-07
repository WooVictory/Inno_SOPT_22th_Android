package woo.sopt22.seminar2.MineTab

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_mine.*
import kotlinx.android.synthetic.main.fragment_mine.view.*
import woo.sopt22.seminar2.MineTabAll
import woo.sopt22.seminar2.R

class MineTab : Fragment(), View.OnClickListener{
    override fun onClick(v: View?) {
        when(v!!){
            mine_tab_x_btn->{
                mine_tab_popup.visibility = GONE
            }
            mine_tab_all_btn->{
                replaceFragment(MineTabAll())
            }
            mine_tab_align_btn->{
                replaceFragment(MineTabAlign())

            }
            mine_tab_tag_btn->{
                replaceFragment(MineTabTag())

            }
            mine_tab_save_btn->{
                startActivity(Intent(context, MineSaveActivity::class.java))

            }
            mine_tab_invite_btn->{
                Toast.makeText(context,"친구 초대하세요.",Toast.LENGTH_LONG).show()
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_mine, container, false)

        v.mine_tab_frame
        v.mine_tab_x_btn.setOnClickListener(this)
        v.mine_tab_all_btn.setOnClickListener(this)
        v.mine_tab_align_btn.setOnClickListener(this)
        v.mine_tab_invite_btn.setOnClickListener(this)
        v.mine_tab_tag_btn.setOnClickListener(this)
        v.mine_tab_save_btn.setOnClickListener(this)
        addFragment(MineTabAll())

        return v

    }


    fun addFragment(fragment : Fragment){
        if(!fragment.isAdded){
            val fm = childFragmentManager
            val transaction = fm.beginTransaction()
            transaction.add(R.id.mine_tab_frame, fragment)
            transaction.commit()
        }

    }

    fun replaceFragment(fragment : Fragment){
        if(!fragment.isAdded){
            val fm = childFragmentManager
            val transaction = fm.beginTransaction()
            transaction.replace(R.id.mine_tab_frame, fragment)
            transaction.commit()
        }

    }






}


