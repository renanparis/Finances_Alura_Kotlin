package com.renanparis.finances.ui.activity

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.renanparis.finances.R
import com.renanparis.finances.delegate.TransactionDelegate
import com.renanparis.finances.extensions.formatToBR
import com.renanparis.finances.model.Transaction
import com.renanparis.finances.model.Type
import com.renanparis.finances.ui.ViewResume
import com.renanparis.finances.ui.adapter.ListTransactionAdapter
import com.renanparis.finances.ui.dialog.InsertFormRevenueDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.lang.NumberFormatException
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

@Suppress("MoveLambdaOutsideParentheses")
class ListTransactionsActivity : AppCompatActivity() {

    private val transactions : MutableList<Transaction> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)
        configAdapter()
        configViewResume()

        lista_transacoes_adiciona_receita.setOnClickListener {
            InsertFormRevenueDialog(window.decorView as ViewGroup, this)
                .configDialog(object : TransactionDelegate{
                    override fun delegate(transaction: Transaction) {
                        updateTransactions(transaction)
                        lista_transacoes_adiciona_menu.close(true)
                    }

                })
        }

    }

    private fun updateTransactions(transaction: Transaction) {
        transactions.add(transaction)
        configViewResume()
        configAdapter()
    }

    private fun configViewResume() {
        val view = window.decorView
        val viewResume = ViewResume(view, this.transactions, this)
        viewResume.updateResumeView()
    }


    private fun configAdapter() {
        val arrayAdapter = ListTransactionAdapter(this.transactions, this)
        lista_transacoes_listview.adapter = arrayAdapter
    }

}