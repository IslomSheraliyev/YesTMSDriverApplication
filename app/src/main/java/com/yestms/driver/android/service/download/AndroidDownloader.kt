package com.yestms.driver.android.service.download

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri

class AndroidDownloader(
    private val context: Context
) {

    private val downloadManager = context.getSystemService(DownloadManager::class.java)

    fun downloadFile(url: String, name: String): Long {
        return downloadManager.enqueue(
            DownloadManager.Request(url.toUri())
                .setMimeType("application/zip")
                .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setTitle(name)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, name)
        )
    }
}
