package com.renanparis.finances.ui

import android.view.View
import com.renanparis.finances.extensions.formatToBrCurrency
import com.renanparis.finances.model.Resume
import com.renanparis.finances.model.Transaction
import kotlinx.android.synthetic.main.resumo_card.view.*

class ViewResume(
    private val view: View,
    transactions: List<Transaction>
) {

    private val resume = Resume(transactions)

    fun addRevenueOnResume (){
        val transactionRevenue = resume.revenue()
        view.resumo_card_receita.text = transactionRevenue.formatToBrCurrency()
    }

    fun addExpensesOnResume (){
        val transactionExpenses = resume.expenses()
        view.resumo_card_despesa.text = transactionExpenses.formatToBrCurrency()
    }

    fun addTotalValueOnresume(){

        val totalTransaction = resume.totalValue()
        view.resumo_card_total.text = totalTransaction.formatToBrCurrency()

    }
}