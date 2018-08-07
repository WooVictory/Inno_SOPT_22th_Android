package woo.sopt22.seminar5

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {


  /*  var seek_bar: Int = 0
    var myHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message?) {

            if (msg!!.what == 0) {
                if (seek_bar < 1000) {
                    seek_bar++
                    music_seekbar.progress = seek_bar
                }
            }
        }
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        music_seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

        music_seekbar.setProgress(music_seekbar.progress+29,true)

/*        val thread = object : Thread() {
            override fun run() {
                for (i in 1..1000) {
                    try {
                        Thread.sleep(100)
                        myHandler.sendEmptyMessage(0)
                    }catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }


            }
        }

        thread.start()*/
    }
}
