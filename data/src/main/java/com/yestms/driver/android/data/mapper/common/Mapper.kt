package uz.fitgroup.gameportal.data.mappers

import java.text.SimpleDateFormat
import java.util.Date

typealias Mapper<T, R> = (T) -> R

fun Int?.or0() = this ?: 0
fun Long?.or0() = this ?: 0
fun Double?.or0() = this ?: 0.0
fun Boolean?.orFalse() = this ?: false
fun String.mapToDateTime(): Date {
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
    return simpleDateFormat.parse(this)
}
fun String.mapToDate(): Date {
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
    return simpleDateFormat.parse(this)
}

val Date.MIN: Date get() = Date(0)