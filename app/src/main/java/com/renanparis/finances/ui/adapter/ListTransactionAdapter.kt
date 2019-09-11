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

        addValue(transaction, createdView)
        addIcon(transaction, createdView)
        addCategory(createdView, transaction)
        addDate(transaction)


        return createdView
    }

    private fun addDate(transaction: Transaction) {
        transaction.date.formatToBR()
    }

    private fun addCategory(
        createdView: View,
        transaction: Transaction
    ) {
        createdView.transacao_categoria.text = transaction.category.limitsStringOnUntil(
            limitCategory
        )
    }

    private fun addValue(
        transaction: Transaction,
        createdView: View
    ) {
        val cor: Int = colorBy(transaction.type)
        createdView.transacao_valor.setTextColor(cor)
        createdView.transacao_valor.text =
            transaction.value.formatToBrCurrency()
    }

    private fun colorBy(type: Type): Int {

        if (type == Type.EXPENSES) {

            return ContextCompat.getColor(
                context,
                R.color.despesa
            )
        }
        return ContextCompat.getColor(
            context,
            R.color.receita
        )
    }

    private fun addIcon(transaction: Transaction, createdView: View){

        val icon: Int = iconBy(transaction.type)
        createdView.transacao_icone
            .setBackgroundResource(icon)
    }

    private fun iconBy(type: Type): Int {
        if (type == Type.EXPENSES) {

            return R.drawable.icone_transacao_item_despesa

        }
        return R.drawable.icone_transacao_item_receita

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