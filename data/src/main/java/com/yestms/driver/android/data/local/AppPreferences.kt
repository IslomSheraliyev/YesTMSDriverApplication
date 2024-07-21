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

    var fullName: String
        get() = preferences.getString(AppPreferences::fullName.name, "") ?: ""
        set(value) {
            preferences.edit()?.putString(AppPreferences::fullName.name, value)?.apply()
        }

    var currentRoleId: Int
        get() = preferences.getInt(AppPreferences::currentRoleId.name, -1)
        set(value) {
            preferences.edit()?.putInt(AppPreferences::currentRoleId.name, value)?.apply()
        }


}
