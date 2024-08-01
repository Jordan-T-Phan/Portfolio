package com.example.ticketmaster30

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.IBinder
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Random
import android.Manifest
import android.util.Log
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Date

class NotificationService : Service() {
    //   private val TAG = "notificationService"
    private lateinit var fireBaseDb: FirebaseFirestore


    private var stopRequest = false

    companion object {

        private const val CHANNEL_ID = "notification_service_channel"
        private const val CHANNEL_NAME = "NOTIFICATION SERVICE CHANNEL"
        private const val TAG = "NotificationService"
    }

    override fun onBind(intent: Intent?): IBinder? = null
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "NOPROBLEM")
        Toast.makeText(this, "SERVICE has started", Toast.LENGTH_SHORT)
        fireBaseDb = FirebaseFirestore.getInstance()

        Thread {
            while (!stopRequest) {



                fireBaseDb.collection("VenueEvents").orderBy("id").get()
                    .addOnSuccessListener { documents ->
                        for (document in documents) {
                            var time =   document.get("time").toString()
                            time = time.substring(6)
                            Log.d(TAG,time)
                            System.out.println(time)
                            val currentDate = Calendar.getInstance().time
                            val formatter1 = SimpleDateFormat("dd")
                            val formatter2 = SimpleDateFormat("MM")
                            val formatter3 = SimpleDateFormat("yyyy")
                            val formatter4 = SimpleDateFormat("hh")
                            val currentDay = Integer.parseInt(formatter1.format(currentDate))
                            val currentMonth = Integer.parseInt(formatter2.format(currentDate))
                            val currentYear = Integer.parseInt(formatter3.format(currentDate))
                            val currentHour = Integer.parseInt(formatter4.format(currentDate))
                            val displayDate = SimpleDateFormat("yyyy/MM/dd @ h:mm aa")
                            val newdisplayDate = displayDate.parse(time)
                            val eventDay = Integer.parseInt(formatter1.format(newdisplayDate))
                            val eventMonth =Integer.parseInt(formatter2.format(newdisplayDate))
                            val eventYear = Integer.parseInt(formatter3.format(newdisplayDate))
                            val eventHour = Integer.parseInt(formatter4.format(newdisplayDate))

                            System.out.println(formatter1.format(currentDate))
                            System.out.println(formatter2.format(currentDate))
                            System.out.println(formatter1.format(newdisplayDate))
                            System.out.println(formatter2.format(newdisplayDate))
                            val eventDays = (eventYear-1)*365+(eventMonth-1)*30 +eventDay + eventHour/24
                            val currentDays = (currentYear-1)*365+(currentMonth-1)*30 +currentDay + eventHour/24
                            val difference = eventDays-currentDays
                            System.out.println(difference)
                            var negative = false
                            if(difference <0) {
                                 negative = true
                            }else{
                                 negative = false}
                            /*  System.out.println()

                            val displayDate = java.text.SimpleDateFormat("yyyy/MM/dd @ h:mm aa").parse(time)
                            val newMonth = formatter1.format(time)

                            val newDay = formatter2.format(time)

//                            val currentDay = java.text.SimpleDateFormat("dd").format(sdf)
  //                          val currentMonth = java.text.SimpleDateFormat("MM").format(sdf)
                            Log.d(TAG,"${newMonth} ${newDay}")
*/
                            if(difference >= 0 && !negative){
                            makeNotification(
                                document.get("eventName").toString(),
                                document.get("venueName").toString(),
                                document.get("address").toString(),
                                document.get("time").toString(),
                                document.get("id").toString(),difference
                            )}
                            Log.d(TAG, document.get("id").toString())


                        }

                    }
                Thread.sleep(86400000)
            }
        }.start()
        return START_STICKY
    }

    private fun makeNotification(

        eventName: String,
        venueName: String,
        address: String,
        time: String,
        id: String,
        difference: Int
    ) {
        Log.d(TAG, "MAKENOTIFCATIONS WENT THROUGH")
        createNotificationChannel()
        createNotification(eventName, venueName, address, time, id, difference)
    }

    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val descriptionText = "FAVORITE CHANNEL"
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = descriptionText

            }


            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

        }


    }

    private fun createNotification(
        eventName: String,
        venueName: String,
        address: String,
        time: String,
        id: String,
        difference: Int
    ) {
        val intent = Intent(this, Memory::class.java)
        intent.putExtra("ID", id)
        val flag = when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> FLAG_UPDATE_CURRENT or FLAG_IMMUTABLE
            else -> FLAG_UPDATE_CURRENT

        }
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, flag)
        var cText = ""
        if(difference == 0){
            cText = "${eventName} at ${venueName} is Today"

        }
        else
        {
            cText = "${eventName} at ${venueName} is in ${difference} days"

        }
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_circle_notifications_24).setContentTitle(eventName)
            .setContentText(cText)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT).setContentIntent(pendingIntent)
            .setAutoCancel(true).build()

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val notificationID = Random().nextInt()
            NotificationManagerCompat.from(this).notify(notificationID, notification)
        }


    }


}