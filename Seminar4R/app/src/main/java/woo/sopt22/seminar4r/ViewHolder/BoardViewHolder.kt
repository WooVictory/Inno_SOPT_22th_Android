package woo.sopt22.seminar4r.ViewHolder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.board_item.view.*
import org.w3c.dom.Text
import woo.sopt22.seminar4r.R

class BoardViewHolder(itemView : View?) : RecyclerView.ViewHolder(itemView) {

    var boardProfile : ImageView = itemView!!.findViewById(R.id.board_item_profile)
    var boardId : TextView = itemView!!.findViewById(R.id.board_item_id)
    var boardTitle : TextView = itemView!!.findViewById(R.id.board_item_title)
    var boardContent : TextView = itemView!!.findViewById(R.id.board_item_content)
    var boardTime : TextView = itemView!!.findViewById(R.id.board_item_time)

}