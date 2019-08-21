package com.renanparis.finances.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.renanparis.finances.R
import java.util.zip.Inflater

class ListTransactionAdapter(
    private val transactions: List<String>,
    private val context: Context
) : BaseAdapter() {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return LayoutInflater.from(context).inflate(R.layout.transacao_item, parent, false)
    }

    override fun getItem(position: Int): Any {
        return transactions[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {

        return transactions.size
    }
}