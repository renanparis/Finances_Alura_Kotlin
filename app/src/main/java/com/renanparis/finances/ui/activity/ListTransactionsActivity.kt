package com.renanparis.finances.ui.activity

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.renanparis.finances.R
import com.renanparis.finances.extensions.formatToBR
import com.renanparis.finances.model.Transaction
import com.renanparis.finances.model.Type
import com.renanparis.finances.ui.ViewResume
import com.renanparis.finances.ui.adapter.ListTransactionAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.util.*

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
          val today =   Calendar.getInstance()

            viewCreated.form_transacao_data.setText(today.formatToBR())

            val year = 2019
            val month = 8
            val day = 5

            viewCreated.form_transacao_data.setOnClickListener {DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

                    val dateSelected = Calendar.getInstance()

                    dateSelected.set(year,  month, dayOfMonth)

                    viewCreated.form_transacao_data.setText(dateSelected.formatToBR())

                }, year, month, day).show()}

            val adapter = ArrayAdapter.createFromResource(this,
                R.array.categorias_de_receita, android.R.layout.simple_spinner_dropdown_item)

            viewCreated.form_transacao_categoria.adapter = adapter

            AlertDialog.Builder(this)
                .setTitle(R.string.adiciona_receita)
                .setView(viewCreated)
                .setPositiveButton("Adicionar", null)
                .setNegativeButton("Cancelar", null)
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