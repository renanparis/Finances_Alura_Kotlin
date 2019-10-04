package com.renanparis.finances.ui.dialog

import android.content.Context
import android.view.ViewGroup
import com.renanparis.finances.R
import com.renanparis.finances.model.Type

@Suppress("MoveLambdaOutsideParentheses")
class InsertTransactionDialog(
    viewGroup: ViewGroup,
    context: Context
) : DialogTransactionForm(context, viewGroup) {
    override val positiveButtonTitle: String
        get() = "Adicionar"

    override fun titleBy(type: Type): Int {
        if (type == Type.REVENUE) {
            return R.string.adiciona_receita
        }

        return R.string.adiciona_despesa
    }
}