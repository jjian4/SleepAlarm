package jameskjiang.com.sleepreminders

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

//Used to send notification when set time is reached
class TimeBroadcastReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        if(intent!!.action.equals("com.tester.alarmmanager")) {
            var bundle = intent.extras
            Toast.makeText(context, bundle.getString("message"), Toast.LENGTH_LONG).show()
        }

        //if phone is restarted broadcast is lost
        else if(intent!!.action.equals("android.intent.action.BOOT_COMPLETED")) {
            val saveData = TimeSaveData(context!!)
            saveData.setAlarm()
        }

    }
}