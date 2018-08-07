package woo.sopt22.preview_seminar5

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.Toast
import io.netopen.hotbitmapgg.library.view.RingProgressBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {


    var progress_bar: Int = 0
    var myHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message?) {

            if (msg!!.what == 0) {
                if (progress_bar < 1000) {
                    progress_bar++
                    RingprogressBar.progress = progress_bar
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RingprogressBar.setOnProgressListener {
            object : RingProgressBar.OnProgressListener {
                override fun progressToComplete() {
                    Toast.makeText(applicationContext, "Completed!!", Toast.LENGTH_LONG).show()
                }
            }
        }

        // new Thread() - > object : Thread 이렇게 쓴다. new 대신에 object를 사용!!
        val thread = object : Thread() {
            override fun run() {
                for (i in 1..1000) {
                    try {
                        Thread.sleep(50)
                        myHandler.sendEmptyMessage(0)
                    }catch (e: InterruptedException) {
                    e.printStackTrace()
                    }
                }


            }
        }

        thread.start()
    }
}
