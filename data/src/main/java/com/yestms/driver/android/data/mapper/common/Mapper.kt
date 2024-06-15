package com.yestms.driver.android.data.mapper.common


typealias Mapper<T, R> = (T) -> R

fun Int?.or0() = this ?: 0
fun Long?.or0() = this ?: 0
fun Double?.or0() = this ?: 0.0
fun Boolean?.orFalse() = this ?: false