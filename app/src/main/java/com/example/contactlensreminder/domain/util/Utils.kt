package com.example.contactlensreminder.domain.util

import java.text.SimpleDateFormat
import java.time.ZoneId
import java.util.*

fun getLocalHour(): Int {
    val date = Date()
    val timeZone = TimeZone.getTimeZone(ZoneId.systemDefault())
    val fmt = SimpleDateFormat("HH", Locale.ENGLISH)
    fmt.timeZone = timeZone
    return fmt.format(date).toInt()
}