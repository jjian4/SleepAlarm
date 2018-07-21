package me.jamesjiang.sleepalarm

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import java.util.*

//Used to send notification when set time is reached
class TimeBroadcastReceiver: BroadcastReceiver() {

    private var notificationManager: NotificationManager? = null

    override fun onReceive(context: Context?, intent: Intent?) {

        if(intent!!.action.equals("com.tester.alarmmanager")) {
            //Toast.makeText(context, bundle.getString("message"), Toast.LENGTH_LONG).show()
            sendNotification(context!!)
        }

        //if phone is restarted broadcast is lost, so set alarm again using shared preferences data
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

        //Go to PhrasesActivity when notification is clicked
        val resultIntent = Intent(context, PhrasesActivity::class.java)
        val pendingIntentPhrases = PendingIntent.getActivity(
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

        //Get current time
        val calendar = Calendar.getInstance()
        val currentTime = formatTime(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE))


        //Add action to notification
        val icon = Icon.createWithResource(context, android.R.drawable.ic_dialog_info)
        val openAction = Notification.Action.Builder(icon, "Open", pendingIntentPhrases).build()

        //Build the notification
        val notification = Notification.Builder(context, channelID)
                .setContentTitle(currentTime + phrase)
                .setContentText("Click to customize your reminders")
                .setSmallIcon(R.drawable.alarm_notification)
                .setChannelId(channelID)
                .setContentIntent(pendingIntentPhrases)
                .setActions(openAction)
                .build()

        notificationManager?.notify(notificationID, notification)
    }



    //Used to combine time components, place at beginning of notification
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
            return hourStr + minStr + " PM: "
        } else {
            return hourStr + minStr + " AM: "
        }
    }
}