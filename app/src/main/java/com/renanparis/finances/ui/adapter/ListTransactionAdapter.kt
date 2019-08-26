package com.renanparis.finances.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.renanparis.finances.R
import com.renanparis.finances.model.Transaction
import kotlinx.android.synthetic.main.transacao_item.view.*
import java.text.SimpleDateFormat

class ListTransactionAdapter(
    private val transactions: List<Transaction>,
    private val context: Context
) : BaseAdapter() {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val createdView = LayoutInflater.from(context)
            .inflate(R.layout.transacao_item, parent, false)
        val transaction = transactions[position]

        createdView.transacao_valor.text = transaction.value.toString()
        createdView.transacao_categoria.text = transaction.category

        val formatBr = "dd/MM/yyyy"
        val format = SimpleDateFormat(formatBr)
        val dateFormated = format.format(transaction.date.time)
        createdView.transacao_data.text = dateFormated
        return createdView
    }

    override fun getItem(position: Int): Transaction {
        return transactions[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {

        return transactions.size
    }
}