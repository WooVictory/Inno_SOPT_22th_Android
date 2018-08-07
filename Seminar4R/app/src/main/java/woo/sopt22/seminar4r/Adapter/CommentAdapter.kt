package woo.sopt22.seminar4r.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import woo.sopt22.seminar4r.Get.GetCommentResponseData
import woo.sopt22.seminar4r.R
import woo.sopt22.seminar4r.ViewHolder.CommentViewHolder


class CommentAdapter(var commentItems : ArrayList<GetCommentResponseData>) : RecyclerView.Adapter<CommentViewHolder>() {

    private lateinit var onItemClick : View.OnClickListener

    fun setOnItemClick(l : View.OnClickListener){
        onItemClick = l
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val mainView : View = LayoutInflater.from(parent.context).inflate(R.layout.comment_item,parent,false)
        mainView.setOnClickListener(onItemClick)
        return CommentViewHolder(mainView)

    }

    override fun getItemCount(): Int = commentItems.size

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.commentWriter.text = commentItems[position].user_id
        holder.commentContent.text = commentItems[position].comment_content
        holder.commentWriteTime.text = commentItems[position].comment_writetime!!.substring(0,10)
    }
}