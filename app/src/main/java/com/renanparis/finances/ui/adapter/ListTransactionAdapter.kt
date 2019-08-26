package com.renanparis.finances.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.renanparis.finances.R
import com.renanparis.finances.model.Transaction
import kotlinx.android.synthetic.main.transacao_item.view.*

class ListTransactionAdapter(
    private val transactions: List<Transaction>,
    private val context: Context
) : BaseAdapter() {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val createdView = LayoutInflater.from(context)
            .inflate(R.layout.transacao_item, parent, false)
        val transaction = transactions[position]

        createdView.transacao_valor.text = transaction.getValue().toString()
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