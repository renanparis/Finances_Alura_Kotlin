package com.renanparis.finances.ui.activity

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.renanparis.finances.R
import com.renanparis.finances.delegate.TransactionDelegate
import com.renanparis.finances.model.Transaction
import com.renanparis.finances.model.Type
import com.renanparis.finances.ui.ViewResume
import com.renanparis.finances.ui.adapter.ListTransactionAdapter
import com.renanparis.finances.ui.dialog.InsertTransactionDialog
import com.renanparis.finances.ui.dialog.UpdateTransactionDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

@Suppress("MoveLambdaOutsideParentheses")
class ListTransactionsActivity : AppCompatActivity() {

    private val transactions: MutableList<Transaction> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)
        configAdapter()
        configViewResume()
        configFAB()

    }

    private fun configFAB() {
        lista_transacoes_adiciona_receita.setOnClickListener {
            showTransactionDialog(Type.REVENUE)
        }


        lista_transacoes_adiciona_despesa.setOnClickListener {
            showTransactionDialog(Type.EXPENSES)
        }
    }

    private fun showTransactionDialog(type: Type) {

        InsertTransactionDialog(window.decorView as ViewGroup, this)
            .showInsertDialog(type, object : TransactionDelegate {
                override fun delegate(transaction: Transaction) {
                    transactions.add(transaction)
                    updateTransactions()
                    lista_transacoes_adiciona_menu.close(true)
                }

            })
    }

    private fun updateTransactions() {
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
        lista_transacoes_listview.setOnItemClickListener { parent, view, position, id -> 
            val transactionClicked = transactions[position]
            UpdateTransactionDialog(window.decorView as ViewGroup, this)
                .showUpdateDialog(transactionClicked, object : TransactionDelegate{
                    override fun delegate(transaction: Transaction) {
                        transactions[position] = transaction
                        updateTransactions()
                    }
                })
        }
    }

}