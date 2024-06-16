package com.yestms.driver.android.components.utils

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

object DateConverter {
    fun getNoticeDate(date: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
        val outputFormat = SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH)
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")

        return inputFormat.parse(date)?.let { outputFormat.format(it) } ?: "Unknown"
    }

    fun getNoticeTime(date: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
        val outputFormat = SimpleDateFormat("hh:mm a", Locale.ENGLISH)
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")

        return inputFormat.parse(date)?.let { outputFormat.format(it) } ?: "Unknown"
    }
}
