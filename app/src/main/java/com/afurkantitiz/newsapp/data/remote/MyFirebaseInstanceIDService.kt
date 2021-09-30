package com.afurkantitiz.newsapp.data.remote

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.afurkantitiz.newsapp.R
import com.afurkantitiz.newsapp.ui.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

const val channelId = "CHANNEL"
const val channelName = "pushnotification"

class MyFirebaseInstanceIDService : FirebaseMessagingService() {

    lateinit var tittle: String
    lateinit var message: String
    lateinit var manager: NotificationManager

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        tittle = remoteMessage.notification!!.title!!
        message = remoteMessage.notification!!.body!!

        manager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        sendNotification()
    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private fun sendNotification() {
        val intent = Intent(this, MainActivity::class.java)

        intent.putExtra("title", tittle)
        intent.putExtra("message", message)

        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(this, channelId)
            .setContentTitle(tittle)
            .setSmallIcon(R.drawable.ic_author)
            .setAutoCancel(true)
            .setContentText(message)

        val pendingIntent =
            PendingIntent.getActivity(applicationContext, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        builder.setContentIntent(pendingIntent)
        manager.notify(0, builder.build())
    }
}