package com.renanparis.finances.ui.dialog

import android.content.Context
import android.view.ViewGroup
import com.renanparis.finances.R
import com.renanparis.finances.extensions.formatToBR
import com.renanparis.finances.model.Transaction
import com.renanparis.finances.model.Type

@Suppress("MoveLambdaOutsideParentheses")
class UpdateTransactionDialog(
    viewGroup: ViewGroup,
    private val context: Context
) : DialogTransactionForm(context, viewGroup) {
    override val positiveButtonTitle: String
        get() = "Alterar"

    override fun titleBy(type: Type): Int {
        if (type == Type.REVENUE) {
            return R.string.altera_receita
        }

        return R.string.altera_despesa
    }

    fun showUpdateDialog(transaction: Transaction, delegate: (transaction: Transaction) -> Unit) {
        val type = transaction.type
        super.showDialog(type, delegate)
        initializeFields(transaction)
    }

    private fun initializeFields(
        transaction: Transaction

        ) {
        val type = transaction.type
        initializeValueField(transaction)
        initializeDateField(transaction)
        initializeCategoryField(type, transaction)
    }

    private fun initializeCategoryField(
        type: Type,
        transaction: Transaction
    ) {
        val categoriesReturned = context.resources.getStringArray(categoryBy(type))
        val categoryPosition = categoriesReturned.indexOf(transaction.category)
        fieldCategory.setSelection(categoryPosition, true)
    }

    private fun initializeDateField(transaction: Transaction) {
        fieldDate.setText(transaction.date.formatToBR())
    }

    private fun initializeValueField(transaction: Transaction) {
        fieldValue.setText(transaction.value.toString())
    }


}