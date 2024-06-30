package com.yestms.driver.android.domain.enums

enum class DriverDetailsLoadStatus(val status: String) {
    PendingUnseen("pending unseen"),
    PendingSeen("pending seen"),
    Approved("approved"),
    Rejected("rejected"),
    PendingPickUp("pending pick up"),
    InTransit("in transit"),
    PendingDropOff("pending drop off"),
    PendingPaperWork("pending paperwork"),
    Completed("completed"),
    PaperWorkFailed("paperwork failed"),
    Canceled("canceled"),
    Unknown("Unknown");

    companion object {
        fun getStatus(operator: String): DriverDetailsLoadStatus {
            return entries.find { it.status == operator } ?: Unknown
        }
    }
}