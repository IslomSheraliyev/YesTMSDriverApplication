package com.yestms.driver.android.data.enums

enum class ThemeMode(val id: Int) {
    DAY(1),
    NIGHT(2);

    companion object {
        fun getThemeMode(id: Int): ThemeMode {
            return entries.find { it.id == id } ?: DAY
        }
    }
}