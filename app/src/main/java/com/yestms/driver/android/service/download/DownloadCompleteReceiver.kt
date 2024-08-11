package com.yestms.driver.android.service.download

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class DownloadCompleteReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == "android.intent.action.DOWNLOAD_COMPLETE") {
            val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1L)

            Toast.makeText(
                context,
                if (id != -1L) "Downloaded Successfully" else "Unknown Error Occurred",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}