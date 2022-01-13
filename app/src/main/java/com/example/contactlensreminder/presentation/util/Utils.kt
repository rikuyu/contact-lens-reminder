package com.example.contactlensreminder.presentation.util

import java.text.SimpleDateFormat
import java.util.*

fun getExpirationDate(lensPeriod: Int): String {
    val calender = Calendar.getInstance()
    calender.time = Date()
    calender.add(Calendar.DATE, lensPeriod)
    val date: Date = calender.time
    val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH)
    return simpleDateFormat.format(date)
}