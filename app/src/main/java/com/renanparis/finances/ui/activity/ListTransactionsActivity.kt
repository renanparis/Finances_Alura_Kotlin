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
    private val viewActivity by lazy {
        window.decorView

    }

    private val viewGroupActivity by lazy {
        viewActivity as ViewGroup
    }

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

        InsertTransactionDialog(viewGroupActivity, this)
            .showDialog(type, object : TransactionDelegate {
                override fun delegate(transaction: Transaction) {
                    add(transaction)
                    lista_transacoes_adiciona_menu.close(true)
                }

            })
    }

    private fun add(transaction: Transaction) {
        transactions.add(transaction)
        updateTransactions()
    }

    private fun updateTransactions() {
        configViewResume()
        configAdapter()
    }

    private fun configViewResume() {
        val view = viewActivity
        val viewResume = ViewResume(view, this.transactions, this)
        viewResume.updateResumeView()
    }


    private fun configAdapter() {
        val arrayAdapter = ListTransactionAdapter(this.transactions, this)
        with(lista_transacoes_listview) {
            adapter = arrayAdapter
            setOnItemClickListener { _, _, position, _ ->
                val transactionClicked = transactions[position]
                showUpdateTransactionDialog(transactionClicked, position)
            }
        }

    }

    private fun showUpdateTransactionDialog(
        transactionClicked: Transaction,
        position: Int
    ) {
        UpdateTransactionDialog(viewGroupActivity, this)
            .showUpdateDialog(transactionClicked, object : TransactionDelegate {
                override fun delegate(transaction: Transaction) {
                    update(transaction, position)
                }
            })
    }

    private fun update(transaction: Transaction, position: Int) {
        transactions[position] = transaction
        updateTransactions()
    }

}