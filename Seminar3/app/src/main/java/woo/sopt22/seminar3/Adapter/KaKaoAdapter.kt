package woo.sopt22.seminar3.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import woo.sopt22.seminar3.Data.KaKaoData
import woo.sopt22.seminar3.R
import woo.sopt22.seminar3.ViewHolder.KaKaoViewHolder
import android.content.Intent
import android.text.Editable
import android.text.Layout
import android.text.TextWatcher
import woo.sopt22.seminar3.Review1.ProfileActivity
import woo.sopt22.seminar3.ViewHolder.KaKaoHeaderHolder
import java.util.Locale.filter


class KaKaoAdapter(private var kakaoItems : ArrayList<KaKaoData>, private var context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){


    /*FIXME
    * (private var kakaoItems : ArrayList<KaKaoData>) : 생성자를 만든 것이다.
    * */

    private lateinit var onItemClick: View.OnClickListener
    private val TYPE_HEADER : Int = 0
    private val TYPE_ITEM : Int = 1
    private var filterData : ArrayList<KaKaoData> = kakaoItems

    fun setOnItemClickListener(l : View.OnClickListener){
        onItemClick = l
    }

    // 아이템 하나 하나에 접근해서 click 이벤트를 적용할 수 있도록 클릭 이벤트를 받는 함수를 만들었다.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // 이 Adapter가 쓸 ViewHolder가 무엇인지를 반환하는 함수

        if(viewType == TYPE_ITEM){
            val mainView : View = LayoutInflater.from(parent.context).inflate(R.layout.kakao_item,parent,false)
            mainView.setOnClickListener(onItemClick)
            //mainView.item_profile_image.setOnClickListener(onImageClick)
            return KaKaoViewHolder(mainView)
        }else{
            val headerView : View = LayoutInflater.from(parent.context).inflate(R.layout.header,parent,false)
            return KaKaoHeaderHolder(headerView)
        }



        // 나는 View를 쓸 것이고 ViewHolder는 KaKaoViewHolder를 쓸 것이다.
    }


    override fun getItemViewType(position: Int): Int {
        if(isPositionHeader(position)){
            return TYPE_HEADER
        }else{
            return TYPE_ITEM
        }
        return 0
    }
    fun isPositionHeader(position: Int):Boolean{
        return position == TYPE_HEADER
    }
    fun isPositionItem(position: Int): Boolean{
        return position == TYPE_ITEM
    }
    override fun getItemCount(): Int = kakaoItems.size

    // return만 하면 이렇게 위와 같이 한 줄로 생략해서 쓸 수 있다.

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        // 데이터 클래스와 ViewHolder를 이어주는 부분
        // 뷰홀더에 있는 뷰들에 position에 맞는 친구들을 넣어준다.

        if(holder is KaKaoViewHolder){
            val itemHolder : KaKaoViewHolder = holder
            itemHolder.kakaoProfile.setImageResource(kakaoItems.get(position).profile)
            itemHolder.kakaoChattingName.setText(kakaoItems.get(position).name)
            itemHolder.kakaoDate.setText(kakaoItems.get(position).date)
            itemHolder.kakaoPreview.setText(kakaoItems.get(position).preView)
            itemHolder.kakaoProfile.setOnClickListener(View.OnClickListener {
                Toast.makeText(context, kakaoItems[position].name,Toast.LENGTH_LONG).show()
                val intent : Intent = Intent(context, ProfileActivity::class.java)
                intent.putExtra("profileImage",kakaoItems[position].profile)
                intent.putExtra("chattingName",kakaoItems[position].name)
                context.startActivity(intent)

            })
        }else if(holder is KaKaoHeaderHolder){
            val headedHolder : KaKaoHeaderHolder = holder
            headedHolder.headerEdittext.hint = "친구 검색하세요~"


        }





        /*FIXME
        * holder.kakaoPreview.text = kakaoItems[position].preView -> 이런 방식으로 진행해도 된다.
        *
        * */
    }

}