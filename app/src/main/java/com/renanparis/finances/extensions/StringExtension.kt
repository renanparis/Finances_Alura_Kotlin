package com.renanparis.finances.extensions

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*


fun String.limitsStringOnUntil(characters: Int): String {
    val startCharacters = 0

    if (this.length > characters) {
        return "${this.substring(startCharacters, characters)}..."
    }
    return this
}


@SuppressLint("SimpleDateFormat")
fun String.convertToCalendar(): Calendar{

    val format = SimpleDateFormat("dd/MM/yyyy")
    val convertedDate: Date = format.parse(this)
    val date = Calendar.getInstance()
    date.time = convertedDate
    return date
}