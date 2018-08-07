package woo.sopt22.seminar3.ViewHolder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import woo.sopt22.seminar3.R


/*FIXME
* 부모 클래스에 있는 생성자 중에 View 변수를 받는 생성자를
* kaKaoViewHolder에서도 사용하겠다는 의미
* */
class KaKaoViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView){


    /* 뷰들에 대한 변수 정의 */
    var kakaoProfile : ImageView = itemView!!.findViewById(R.id.item_profile_image) as ImageView
    var kakaoChattingName : TextView = itemView!!.findViewById(R.id.item_chatting_name_tv) as TextView // name_tv
    var kakaoDate : TextView = itemView!!.findViewById(R.id.item_date_name_tv) as TextView
    var kakaoPreview : TextView = itemView!!.findViewById(R.id.item_preview_name_tv) as TextView






}