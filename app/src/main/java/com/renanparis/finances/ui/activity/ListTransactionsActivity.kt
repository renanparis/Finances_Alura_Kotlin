package com.renanparis.finances.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.renanparis.finances.R
import com.renanparis.finances.model.Transaction
import com.renanparis.finances.ui.adapter.ListTransactionAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal
import java.util.*

class ListTransactionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)
        val transactions = listOf(Transaction(BigDecimal(20.5), "Comida", Calendar.getInstance()),
            Transaction(BigDecimal(100.0), "Imposto", Calendar.getInstance()))

        val arrayAdapter = ListTransactionAdapter(transactions, this)
        lista_transacoes_listview.adapter = arrayAdapter
    }
}