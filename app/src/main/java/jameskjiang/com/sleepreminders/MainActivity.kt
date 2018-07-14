package jameskjiang.com.sleepreminders

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

//TODO: put actions under notification, option to close the alarm

//Displays and manages the time of day the notification appears
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Go to list of phrases
        button_goto_phrases.setOnClickListener {
            val intent = Intent(this, PhrasesActivity::class.java)
            startActivity(intent)
        }

        //Text displays the set time
        val saveData = TimeSaveData(applicationContext)
        textView_show_time.text = formatTime(saveData.getHour(), saveData.getMin())

        //Show fragment that is used to set time
        button_set_time.setOnClickListener {
            val popTime = PopTimeFragment()
            val fm = this@MainActivity.supportFragmentManager
            popTime.show(fm, "Select Time")
        }
    }

    //Called in fragment, sets notification time
    fun setTime(hours: Int, min: Int) {
        textView_show_time.text = formatTime(hours, min)

        val saveData = TimeSaveData(applicationContext)
        saveData.saveData(hours, min)
        saveData.setAlarm()
    }

    //Used to combine time components into a string
    fun formatTime(hour:Int, min:Int): String {
        var hourStr = hour.toString()
        var pm = false

        if(hour > 11) {
            hourStr = (hour - 12).toString()
            pm = true
        }
        if(hour == 0 || hour == 12) {
            hourStr = "12"
        }

        var minStr = ":$min"
        if(min < 10) {
            minStr = ":0$min"
        }

        if(pm) {
            return hourStr + minStr + " PM"
        } else {
            return hourStr + minStr + " AM"
        }
    }

}
