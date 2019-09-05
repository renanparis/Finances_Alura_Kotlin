package com.renanparis.finances.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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

        lista_transacoes_adiciona_receita.setOnClickListener {
            val view = window.decorView

            val viewCreated =
                LayoutInflater.from(this)
                    .inflate(R.layout.form_transacao, view as ViewGroup, false)

            AlertDialog.Builder(this)
                .setTitle(R.string.adiciona_receita)
                .setView(viewCreated)
                .show()
        }

    }

    private fun configViewResume(transactions: List<Transaction>) {
        val view = window.decorView
        val viewResume = ViewResume(view, transactions, this)
        viewResume.updateResumeView()
    }


    private fun configAdapter(transactions: List<Transaction>) {
        val arrayAdapter = ListTransactionAdapter(transactions, this)
        lista_transacoes_listview.adapter = arrayAdapter
    }

    private fun listTransactionsExample(): List<Transaction> {
        return listOf(
            Transaction(value = BigDecimal(200.5), type = Type.EXPENSES),
            Transaction(BigDecimal(100.0), "Venda de Fábrica de Ração", Type.REVENUE),
            Transaction(value = BigDecimal(5.0), type = Type.EXPENSES),
            Transaction(value = BigDecimal(50.0), category = "Venda", type = Type.REVENUE)
        )
    }
}