package com.yestms.driver.android.data.mapper


typealias Mapper<T, R> = (T) -> R
private const val UNKNOWN = "Unknown"

fun Int?.or0() = this ?: 0
fun String?.orUnknown() = this ?: UNKNOWN
fun Long?.or0() = this ?: 0
fun Double?.or0() = this ?: 0.0
fun Boolean?.orFalse() = this ?: false