package io.github.rikuyu.contactlensreminder.data.util

import java.text.SimpleDateFormat
import java.util.*

fun getExpirationDate(lensPeriod: Int): String {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DATE, lensPeriod)
    val simpleDateFormat = SimpleDateFormat("MM/dd/E", Locale.getDefault())
    val (m, d, day) = simpleDateFormat.format(calendar.time).split("/")
    return "${m.toInt()}/${d.toInt()} ($day)"
}