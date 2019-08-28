package com.renanparis.finances.extensions

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.Locale

fun BigDecimal.formatToBrCurrency(): String {

    val numberFormat = DecimalFormat.getCurrencyInstance(Locale("pt", "br"))

    return numberFormat.format(this).replace("R$", "R$ ")

}