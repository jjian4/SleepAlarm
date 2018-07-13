package jameskjiang.com.sleepreminders

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Go to list of phrases
        button_goto_phrases.setOnClickListener {
            val intent = Intent(this, PhrasesActivity::class.java)
            startActivity(intent)
        }

        //Show fragment that is used to set time
        button_set_time.setOnClickListener {
            val popTime = PopTimeFragment()
            val fm = this@MainActivity.supportFragmentManager
            popTime.show(fm, "Select Time")
        }
    }

    //Called in fragment, sets notification time
    fun setTime(hours: Int, min: Int) {
        textView_show_time.text = hours.toString() + ":" + min.toString()

        val saveData = TimeSaveData(applicationContext)
        saveData.setAlarm(hours, min)
    }

}
