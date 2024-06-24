package com.yestms.driver.android.data.constants

internal object AuthUrl {
    private const val PRE = "auth"
    const val CHECK = "$PRE/check"
    const val LOGIN_DRIVER = "$PRE/login-driver"
}

internal object LoadsUrl {
    const val LOADS = "loads"
    const val GET_LOAD = "loads/{id}"
    const val ALERT_STATUSES = "$LOADS/alert-statuses"
    const val LOADS_UNSEEN_COUNT = "$LOADS/loads-unseen-count"
}

internal object NotificationsUrl {
    const val NOTIFICATIONS = "notices"
    const val NOTIFICATIONS_DELETE_ONE = "$NOTIFICATIONS/{id}"
    const val UNREAD = "$NOTIFICATIONS/unread"
}

internal object SettingsUrl {
    const val SETTINGS = "settings"
    const val COMPANY_SETTINGS = "$SETTINGS/company-settings"
}

internal object UserUrl {
    const val USER = "user"
    const val GET_DRIVER_STATS = "$USER/get-driver-stats/{id}"
}