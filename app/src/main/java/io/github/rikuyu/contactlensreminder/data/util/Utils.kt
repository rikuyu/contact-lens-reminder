package io.github.rikuyu.contactlensreminder.data.util

import java.text.SimpleDateFormat
import java.util.*

fun getExpirationDate(lensPeriod: Int): String {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DATE, lensPeriod)
    val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd:E", Locale.getDefault())
    val (date, day) = simpleDateFormat.format(calendar.time).split(":")
    return "$date ($day)"
}