package woo.sopt22.seminar3.ViewHolder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import woo.sopt22.seminar3.R

class KaKaoHeaderHolder(itemView : View?) : RecyclerView.ViewHolder(itemView) {
    var headerEdittext : EditText = itemView!!.findViewById(R.id.header_editText)
}