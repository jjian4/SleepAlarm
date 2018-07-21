package me.jamesjiang.sleepalarm

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

//TODO: put actions under notification

//Displays and manages the time of day the notification appears
class MainActivity : AppCompatActivity() {

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Go to list of phrases
        button_goto_phrases.setOnClickListener {
            val intent = Intent(this, PhrasesActivity::class.java)
            startActivity(intent)
        }

        //Text displays the set time if time is set
        val saveData = TimeSaveData(applicationContext)
        if(saveData.getHour() != -1) {
            textView_show_time.text = formatTime(saveData.getHour(), saveData.getMin())
            button_cancel_alarm.visibility = View.VISIBLE
        }

        //Show fragment that is used to set time
        button_set_time.setOnClickListener {
            val popTime = PopTimeFragment()
            val initialTime = Bundle()
            //Set initial time in the fragment to the set time if alarm is set
            if(saveData.getHour() != -1) {
                initialTime.putInt("hour", saveData.getHour())
                initialTime.putInt("min", saveData.getMin())
            } else {    //Default is 11pm
                initialTime.putInt("hour", 23)
                initialTime.putInt("min", 0)
            }
            popTime.setArguments(initialTime)

            val fm = this@MainActivity.supportFragmentManager
            popTime.show(fm, "Select Time")
        }

        //Cancel alarm
        button_cancel_alarm.setOnClickListener {
            saveData.cancelAlarm()

            textView_show_time.text = getString(R.string.time_textView)
            textView_show_time.setTextColor(ContextCompat.getColor(applicationContext, R.color.colorAccent))

            //Hide cancel alarm button
            button_cancel_alarm.visibility = View.GONE
        }

    }

    @SuppressLint("RestrictedApi")
    //Called in fragment, sets notification time
    fun setTime(hours: Int, min: Int) {
        textView_show_time.text = formatTime(hours, min)
        textView_show_time.setTextColor(ContextCompat.getColor(applicationContext, R.color.colorPrimaryDark))

        //Show cancel alarm button
        button_cancel_alarm.visibility = View.VISIBLE

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
