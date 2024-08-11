package com.yestms.driver.android.service.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import com.bumptech.glide.Glide
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.yestms.driver.android.R
import java.util.Random

class MyFirebaseNotificationService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.data.isNotEmpty()) {
            try {
                val notification: RemoteMessage.Notification? = remoteMessage.notification
                if (notification != null) {
                    val data: Map<String, String> = remoteMessage.data
                    val clickAction: String? = data["click_action"]
                    val link: Uri? = Uri.parse(data["link"])
                    val photoUrl: String? = data["image"]
                    sendNotification(
                        title = notification.title,
                        body = notification.body,
                        clickAction = clickAction,
                        link = link,
                        photoUrl = photoUrl
                    )
                } else {

                }
                return
            } catch (e: Exception) {

                e.printStackTrace()
            }
        }
    }


    private fun sendNotification(
        title: String?,
        body: String?,
        clickAction: String? = null,
        link: Uri? = null,
        photoUrl: String? = null,
    ) {
        val intent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = link
        }


        intent.flags = (Intent.FLAG_ACTIVITY_NEW_TASK
                or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.getActivity(
                this, 0 /* Request code */, intent,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
        } else {
            PendingIntent.getActivity(
                this, 0 /* Request code */, intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        }
        val channelId = getString(R.string.app_name)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setColorized(true)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .setDefaults(Notification.DEFAULT_LIGHTS)
            .setContentIntent(pendingIntent)


        if (photoUrl != null) {
            try {
                val futureTarget = Glide.with(this)
                    .asBitmap()
                    .load(photoUrl)
                    .submit()
                val bitmap = futureTarget.get()
                notificationBuilder.setLargeIcon(bitmap)
                Glide.with(this).clear(futureTarget)
            } catch (_: Exception) {

            }
        }

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                getString(R.string.app_name),
                NotificationManager.IMPORTANCE_HIGH
            )
            val audioAttributes = AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .build()
            channel.enableVibration(true)
            notificationManager.createNotificationChannel(channel)
        }
        val random = Random()
        notificationManager.notify(
            random.nextInt() /* ID of notification */,
            notificationBuilder.build()
        )
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }
}