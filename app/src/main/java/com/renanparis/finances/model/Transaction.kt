package com.renanparis.finances.model

import java.math.BigDecimal
import java.util.Calendar

class Transaction(private val value: BigDecimal,
                  private val category: String,
                  private val date: Calendar) {

    fun getValue(): BigDecimal{
        return value
    }

}