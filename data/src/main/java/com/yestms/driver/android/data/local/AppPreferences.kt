package com.yestms.driver.android.data.local

import android.content.Context
import android.content.SharedPreferences
import com.yestms.driver.android.data.enums.ThemeMode

object AppPreferences {

    private const val GAME_PORTAL = "tms"

    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(GAME_PORTAL, Context.MODE_PRIVATE)
    }

    // Device Preference
    var isDeviceRegistered: Boolean
        get() = preferences.getBoolean(AppPreferences::isDeviceRegistered.name, false)
        set(value) {
            preferences.edit().putBoolean(AppPreferences::isDeviceRegistered.name, value).apply()
        }


    var themeMode: ThemeMode
        get() = ThemeMode.getThemeMode(preferences.getInt(AppPreferences::themeMode.name, 0))
        set(value) {
            preferences.edit()?.putInt(AppPreferences::themeMode.name, value.id)?.apply()
        }
    var currentUserId: Int
        get() = preferences.getInt(AppPreferences::currentUserId.name, 0)
        set(value) {
            preferences.edit()?.putInt(AppPreferences::currentUserId.name, value)?.apply()
        }
    var accessToken: String
        get() = preferences.getString(AppPreferences::accessToken.name, "") ?: ""
        set(value) {
            preferences.edit()?.putString(AppPreferences::accessToken.name, value)?.apply()
        }
}
