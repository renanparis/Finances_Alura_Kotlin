package com.renanparis.finances.ui

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.renanparis.finances.R
import com.renanparis.finances.extensions.formatToBrCurrency
import com.renanparis.finances.model.Resume
import com.renanparis.finances.model.Transaction
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

class ViewResume(
    private val view: View,
    transactions: List<Transaction>,
    context: Context
) {

    private val resume = Resume(transactions)
    private val colorRevenue = ContextCompat.getColor(context, R.color.receita)
    private val colorExpenses = ContextCompat.getColor(context, R.color.despesa)

    fun updateResumeView(){

        addRevenueOnResume()
        addExpensesOnResume()
        addTotalValueOnResume()
    }

    private fun addRevenueOnResume() {
        val transactionRevenue = resume.revenue

        with(view.resumo_card_receita) {
            setTextColor(colorRevenue)
            text = transactionRevenue.formatToBrCurrency()
        }
    }

   private fun addExpensesOnResume() {
        val transactionExpenses = resume.expenses
        with(view.resumo_card_despesa) {
            setTextColor(colorExpenses)
            text = transactionExpenses.formatToBrCurrency()

        }

    }

    private fun addTotalValueOnResume() {

        val totalTransaction = resume.totalValue
        val color = getColorBy(totalTransaction)
        with(view.resumo_card_total) {
            text = totalTransaction.formatToBrCurrency()
            setTextColor(color)

        }

    }

    private fun getColorBy(totalTransaction: BigDecimal): Int {
        if (totalTransaction >= BigDecimal.ZERO) {
            return colorRevenue
        }

        return colorExpenses
    }
}