package com.renanparis.finances.ui.activity

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.renanparis.finances.R
import com.renanparis.finances.ui.adapter.ListTransactionAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListTransactionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)
        val transactions = listOf("Lanche - R$ 10,00", "Refrigerante - R$ 5,00")
        val arrayAdapter = ListTransactionAdapter(transactions, this)
        lista_transacoes_listview.adapter = arrayAdapter
    }
}