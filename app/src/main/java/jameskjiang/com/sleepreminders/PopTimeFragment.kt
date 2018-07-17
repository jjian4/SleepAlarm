package jameskjiang.com.sleepreminders

import android.os.Build
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TimePicker
import kotlinx.android.synthetic.main.fragment_pop_time.*

//Fragment that pops up when user wants to set a time to receive notification
class PopTimeFragment(): DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var timeView = inflater.inflate(R.layout.fragment_pop_time, container, false)

        var btn_done = timeView.findViewById(R.id.button_time_done) as Button
        var tp = timeView.findViewById(R.id.time_picker) as TimePicker

        //Retrieve previously set hour and minute, if none default is 11pm
        val initialHour = arguments!!.getInt("hour")
        val initialMin = arguments!!.getInt("min")

        tp.hour = initialHour
        tp.minute = initialMin

        btn_done.setOnClickListener {
            val ma = activity as MainActivity
            //different syntax for different SDK versions
            if(Build.VERSION.SDK_INT >= 23) {
                ma.setTime(tp.hour, tp.minute)
            }
            else {
                ma.setTime(tp.currentHour, tp.currentMinute )
            }
            this.dismiss()
        }

        return timeView
    }



}