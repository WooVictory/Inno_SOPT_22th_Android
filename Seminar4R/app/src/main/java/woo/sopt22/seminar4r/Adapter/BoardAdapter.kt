package woo.sopt22.seminar4r.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import woo.sopt22.seminar4r.Get.GetBoardResponseData
import woo.sopt22.seminar4r.R
import woo.sopt22.seminar4r.ViewHolder.BoardViewHolder

class BoardAdapter(var boardItems : ArrayList<GetBoardResponseData>, var requestManager: RequestManager) : RecyclerView.Adapter<BoardViewHolder>() {


    private lateinit var onItemClick : View.OnClickListener
    fun setOnItemClick(l : View.OnClickListener){
        onItemClick = l
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        val mainView = LayoutInflater.from(parent.context).inflate(R.layout.board_item,parent,false)
        mainView.setOnClickListener(onItemClick)
        return BoardViewHolder(mainView)

    }

    override fun getItemCount(): Int = boardItems.size

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {

        /*FIXME
        * holder의 있는 boardID,boardTitle .. 등등은 BoardViewHolder가 보유하고 있는 객체들이다.
        * 즉, board_item.xml 파일에어 우리에게 직접적으로 보여지는 id 인 것이다.
        * boardItems의 user_id, board_title .. 등등은 서버로부터 데이터를 받아서 data 클래스에 저장하고 그것을 기반으로
        * ArrayList를 만든 것이다.
        * */
        holder.boardId.text = boardItems[position].user_id
        holder.boardTitle.text = boardItems[position].board_title
        holder.boardContent.text = boardItems[position].board_content
        holder.boardTime.text = boardItems[position].board_writetime
        requestManager.load(boardItems[position].board_photo).into(holder.boardProfile)

    }
}