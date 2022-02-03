package io.github.rikuyu.contactlensreminder.data.util

import java.text.SimpleDateFormat
import java.util.*

fun getExpirationDate(lensPeriod: Int): String {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DATE, lensPeriod)
    val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd:E", Locale.ENGLISH)
    var (date, day) = simpleDateFormat.format(calendar.time).split(":")
    day = when (day) {
        "Mon" -> "(月)"
        "Tue" -> "(火)"
        "Wed" -> "(水)"
        "Thu" -> "(木)"
        "Fri" -> "(金)"
        "Sat" -> "(土)"
        "Sun" -> "(日)"
        else -> ""
    }
    return "$date $day"
}