package woo.sopt22.hapdongseminar.View.SpecificStore

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import woo.sopt22.hapdongseminar.Model.StoreMenuData
import woo.sopt22.hapdongseminar.R

class MenuAdapter(var menuItems : ArrayList<StoreMenuData>) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val mainView = LayoutInflater.from(parent.context).inflate(R.layout.menu_item, parent, false)
        return MenuViewHolder(mainView)
    }

    override fun getItemCount(): Int = menuItems.size
    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder!!.menuName.text = menuItems[position].menu_name!!
        Log.v("0105",menuItems[position].menu_name)
        holder!!.menuPrice.text = menuItems[position].menu_price!!
    }




    class MenuViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val menuName : TextView =  itemView.findViewById(R.id.menu_name)
        val menuPrice : TextView = itemView.findViewById(R.id.menu_price)

    }
}