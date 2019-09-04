package com.renanparis.finances.model

import java.math.BigDecimal

class Resume(private val transactions: List<Transaction>) {

    fun revenue(): BigDecimal {

        var transactionRevenue = BigDecimal.ZERO
        for (transaction in transactions) {
            if (transaction.type == Type.REVENUE) {
                transactionRevenue = transactionRevenue.plus(transaction.value)

            }
        }

        return transactionRevenue
    }

    fun expenses(): BigDecimal {

        var transactionExpenses = BigDecimal.ZERO
        for (transaction in transactions) {
            if (transaction.type == Type.EXPENSES) {
                transactionExpenses = transactionExpenses.plus(transaction.value)
            }
        }
        return transactionExpenses
    }

    fun totalValue () : BigDecimal{

        return revenue().subtract(expenses())
    }
}