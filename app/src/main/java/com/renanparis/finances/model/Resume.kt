package com.renanparis.finances.model

import java.math.BigDecimal

class Resume(private val transactions: List<Transaction>) {

    val revenue get() = sumBy(Type.REVENUE)

    val expenses get() = sumBy(Type.EXPENSES)

    val totalValue get() = revenue.subtract(expenses)

    fun sumBy(type: Type): BigDecimal {

        val sumTransactionsByType = transactions.filter { it.type == type}
            .sumByDouble {it.value.toDouble() }

        return BigDecimal(sumTransactionsByType)
    }
}