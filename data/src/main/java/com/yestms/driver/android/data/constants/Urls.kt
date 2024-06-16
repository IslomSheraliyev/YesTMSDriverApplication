package com.yestms.driver.android.data.constants

internal object AuthUrl {
    private const val PRE = "auth"
    const val CHECK = "$PRE/check"
    const val LOGIN_DRIVER = "$PRE/login-driver"
    const val LOGIN = "$PRE/login"
}

internal object LoadsUrl {
    const val LOADS = "loads"
    const val ALERT_STATUSES = "$LOADS/alert-statuses"
    const val LOADS_UNSEEN_COUNT = "$LOADS/loads-unseen-count"
}

internal object NoticesUlr {
    const val NOTICES = "notices"
    const val UNREAD = "$NOTICES/unread"
}

internal object SettingsUlr {
    const val SETTINGS = "settings"
    const val COMPANY_SETTINGS = "$SETTINGS/company-settings"
}

internal object UserUrl {
    const val USER = "user"
    const val GET_DRIVER_STATS = "$USER/get-driver-stats"
}