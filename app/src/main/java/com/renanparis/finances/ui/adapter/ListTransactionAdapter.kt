package com.renanparis.finances.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat
import com.renanparis.finances.R
import com.renanparis.finances.extensions.formatToBR
import com.renanparis.finances.extensions.formatToBrCurrency
import com.renanparis.finances.extensions.limitsStringOnUntil
import com.renanparis.finances.model.Transaction
import com.renanparis.finances.model.Type
import kotlinx.android.synthetic.main.transacao_item.view.*

private const val limitCategory = 14

class ListTransactionAdapter(
    private val transactions: List<Transaction>,
    private val context: Context
) : BaseAdapter() {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val createdView = LayoutInflater.from(context)
            .inflate(R.layout.transacao_item, parent, false)
        val transaction = transactions[position]

        if (transaction.type == Type.EXPENSES) {
            createdView.transacao_valor.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.despesa
                )
            )
            createdView.transacao_icone.setBackgroundResource(R.drawable.icone_transacao_item_despesa)
        } else {
            createdView.transacao_valor.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.receita
                )
            )
            createdView.transacao_icone
                .setBackgroundResource(R.drawable.icone_transacao_item_receita)
        }



        createdView.transacao_valor.text =
            transaction.value.formatToBrCurrency()
        createdView.transacao_categoria.text = transaction.category.limitsStringOnUntil(
            limitCategory)
            transaction.date.formatToBR()
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