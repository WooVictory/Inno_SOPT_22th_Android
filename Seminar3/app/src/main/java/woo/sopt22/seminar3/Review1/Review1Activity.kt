package woo.sopt22.seminar3.Review1

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_review1.*
import woo.sopt22.seminar3.Adapter.Review1Adapter
import woo.sopt22.seminar3.Data.Review1Data
import woo.sopt22.seminar3.R

class Review1Activity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {

    }

    lateinit var review1Items : ArrayList<Review1Data>
    lateinit var review1Adapter : Review1Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review1)

        review1Items = ArrayList()



        review1Items.add(Review1Data("5515","[서울]지선버스",Color.GREEN))
        // review1Adapter = Review1Adapter(review1Items, Color.GREEN)
        review1Items.add(Review1Data("720","[서울]간선버스",Color.BLUE))
        // review1Adapter = Review1Adapter(review1Items, Color.BLUE)
        review1Items.add(Review1Data("3100","[서울]광역버스",Color.RED))
        review1Items.add(Review1Data("3600","[서울]광역버스",Color.RED))
        review1Items.add(Review1Data("172","[서울]간선버스",Color.BLUE))
        review1Items.add(Review1Data("1221","[서울]지선버스",Color.GREEN))
        review1Items.add(Review1Data("1224","[서울]지선버스",Color.GREEN))
        review1Items.add(Review1Data("1131","[서울]지선버스",Color.GREEN))
        review1Items.add(Review1Data("1141","[서울]지선버스",Color.GREEN))
        review1Items.add(Review1Data("1400","[서울]광역버스",Color.RED))
        review1Items.add(Review1Data("571","[인천]마을버스",Color.GREEN))
        review1Items.add(Review1Data("30","[인천]지선버스",Color.BLUE))

        review1Adapter = Review1Adapter(review1Items)


        review1_item_list.layoutManager = LinearLayoutManager(this)
        review1_item_list.adapter = review1Adapter





    }
}
