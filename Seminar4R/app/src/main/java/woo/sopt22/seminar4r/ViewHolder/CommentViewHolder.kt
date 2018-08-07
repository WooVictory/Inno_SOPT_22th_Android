package woo.sopt22.seminar4r.ViewHolder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.comment_item.view.*
import woo.sopt22.seminar4r.R

class CommentViewHolder(itemView : View?): RecyclerView.ViewHolder(itemView) {

    var commentWriter : TextView = itemView!!.findViewById(R.id.comment_writer_item)
    var commentContent : TextView = itemView!!.findViewById(R.id.comment_content_item)
    var commentWriteTime : TextView = itemView!!.findViewById(R.id.comment_write_time_item)
}