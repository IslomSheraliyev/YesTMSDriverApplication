package com.yestms.driver.android.data.local

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.yestms.driver.android.data.enums.ThemeMode
import com.yestms.driver.android.domain.model.auth.check.AuthCheckModel
import com.yestms.driver.android.domain.model.auth.check.AuthCheckUserModel

object AppPreferences {

    private const val YES_TMS = "tms"

    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(YES_TMS, Context.MODE_PRIVATE)
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

    var authCheckModel: AuthCheckModel?
        get() {
            val jsonString = preferences.getString(AppPreferences::authCheckModel.name, "") ?: ""
            return Gson().fromJson(jsonString, AuthCheckModel::class.java) ?: null
        }
        set(value) {
            val jsonString = Gson().toJson(value)
            preferences.edit()?.putString(AppPreferences::authCheckModel.name, jsonString)?.apply()
        }


}
