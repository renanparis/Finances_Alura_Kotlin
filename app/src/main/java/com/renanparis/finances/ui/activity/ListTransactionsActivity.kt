package com.renanparis.finances.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.renanparis.finances.R
import com.renanparis.finances.model.Transaction
import com.renanparis.finances.model.Type
import com.renanparis.finances.ui.ViewResume
import com.renanparis.finances.ui.adapter.ListTransactionAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal

class ListTransactionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)
        val transactions: List<Transaction> = listTransactionsExample()
        configAdapter(transactions)
        configViewResume(transactions)

    }

    private fun configViewResume(transactions: List<Transaction>) {
        val view = window.decorView
        val viewResume = ViewResume(view, transactions)
        viewResume.addRevenueOnResume()
        viewResume.addExpensesOnResume()
        viewResume.addTotalValueOnresume()
    }


    private fun configAdapter(transactions: List<Transaction>) {
        val arrayAdapter = ListTransactionAdapter(transactions, this)
        lista_transacoes_listview.adapter = arrayAdapter
    }

    private fun listTransactionsExample(): List<Transaction> {
        return listOf(
            Transaction(value = BigDecimal(20.5), type = Type.EXPENSES),
            Transaction(BigDecimal(100.0), "Venda de Fábrica de Ração", Type.REVENUE),
            Transaction(value = BigDecimal(5.0), type = Type.EXPENSES),
            Transaction(value = BigDecimal(50.0), category = "Venda", type = Type.REVENUE)
        )
    }
}