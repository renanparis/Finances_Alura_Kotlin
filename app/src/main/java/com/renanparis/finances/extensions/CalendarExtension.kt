package com.renanparis.finances.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Calendar.formatToBR(): String {

    val formatBr = "dd/MM/yyyy"
    val format = SimpleDateFormat(formatBr)
    return format.format(this.time)
}