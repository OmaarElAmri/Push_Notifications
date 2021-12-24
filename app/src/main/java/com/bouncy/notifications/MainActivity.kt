package com.bouncy.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SubscriptionManager.from
import android.transition.TransitionInflater.from
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.VISIBILITY_PRIVATE
import androidx.core.app.NotificationManagerCompat
import androidx.databinding.DataBindingUtil
import java.util.Date.from
import com.bouncy.notifications.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    val CHANNEL_ID = "channelID"
    val CHANNEL_NAME = "channelName"
    val NOTIFICATION_ID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        createNotificationChannel()


        val intent = Intent(this,MainActivity::class.java)
        val pendingIntent = TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("awesome")
            .setContentText("this is the content text")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setVisibility(VISIBILITY_PRIVATE)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setVibrate(null)
            .build()

        val notificationManager = NotificationManagerCompat.from(this)

binding.btnnotif.setOnClickListener(){
    notificationManager.notify(NOTIFICATION_ID, notification)
}

    }

    fun createNotificationChannel(){
        val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME,
        NotificationManager.IMPORTANCE_DEFAULT).apply{
            lightColor = Color.GREEN
            enableLights(true)
        }
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)
    }
}