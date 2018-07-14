package jameskjiang.com.sleepreminders

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.widget.Toast
import java.util.*

//Used to send notification when set time is reached
class TimeBroadcastReceiver: BroadcastReceiver() {

    private var notificationManager: NotificationManager? = null

    override fun onReceive(context: Context?, intent: Intent?) {

        if(intent!!.action.equals("com.tester.alarmmanager")) {
            //Toast.makeText(context, bundle.getString("message"), Toast.LENGTH_LONG).show()
            sendNotification(context!!)
        }

        //if phone is restarted broadcast is lost
        else if(intent.action.equals("android.intent.action.BOOT_COMPLETED")) {
            val saveData = TimeSaveData(context!!)
            saveData.setAlarm()
        }

    }


    //Creates the notification channel
    private fun createNotificationChannel(id: String, name: String) {

        val importance = NotificationManager.IMPORTANCE_LOW
        val channel = NotificationChannel(id, name, importance)

        //channel.description = description
        notificationManager?.createNotificationChannel(channel)
    }

    //Used to send a single notification
    fun sendNotification(context: Context) {
        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel("channel_id_01", "Sleep notification")

        val notificationID = 101
        val channelID = "channel_id_01"

        //Go to MainActivity when notification is clicked
        val resultIntent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
                context,
                0,
                resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        )

        //Randomly choose a phrase from the set to use as notification
        val phrasesSharedPreferences = PhrasesSharedPreferences(context)
        val phrasesList = phrasesSharedPreferences.getPhrasesSet().toList()
        val phrase: String
        if(phrasesList.isNotEmpty()) {
            fun ClosedRange<Int>.random() =
                    Random().nextInt((endInclusive + 1) - start) + start

            phrase = phrasesList.get((0 until phrasesList.size).random())
        } else {
            phrase = "Reminder to sleep"
        }

        //Build the notification
        val notification = Notification.Builder(context, channelID)
                .setContentTitle(phrase)
                .setContentText("Click to customize your reminders")
                .setSmallIcon(R.drawable.alarm_notification)
                .setChannelId(channelID)
                .setContentIntent(pendingIntent)
                .build()

        notificationManager?.notify(notificationID, notification)
    }
}