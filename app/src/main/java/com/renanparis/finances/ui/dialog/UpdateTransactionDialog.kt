package com.renanparis.finances.ui.dialog

import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.renanparis.finances.R
import com.renanparis.finances.delegate.TransactionDelegate
import com.renanparis.finances.extensions.convertToCalendar
import com.renanparis.finances.extensions.formatToBR
import com.renanparis.finances.model.Transaction
import com.renanparis.finances.model.Type
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.util.*

@Suppress("MoveLambdaOutsideParentheses")
class UpdateTransactionDialog(
    private val viewGroup: ViewGroup,
    private val context: Context
) {

    private val viewCreated = createLayout()
    private val fieldValue = viewCreated.form_transacao_valor
    private val fieldDate = viewCreated.form_transacao_data
    private val fieldCategory = viewCreated.form_transacao_categoria

    fun showUpdateDialog(transaction: Transaction, transactionDelegate: TransactionDelegate) {
        val type = transaction.type

        configFieldDate()
        configFieldCategory(type)
        configForm(type, transactionDelegate)

        fieldValue.setText(transaction.value.toString())
        fieldDate.setText(transaction.date.formatToBR())
        val categoriesReturned = context.resources.getStringArray(categoryBy(type))
        val categoryPosition = categoriesReturned.indexOf(transaction.category)
        fieldCategory.setSelection(categoryPosition, true)
    }

    private fun configForm(type: Type, transactionDelegate: TransactionDelegate) {
        val title = titleBy(type)

        AlertDialog.Builder(context)
            .setTitle(title)
            .setView(viewCreated)
            .setPositiveButton("Alterar", { _: DialogInterface, _: Int ->
                val valueText = fieldValue.text.toString()
                val value = convertToBigDecimal(valueText)
                val dateText = fieldDate.text.toString()
                val date = dateText.convertToCalendar()
                val category = fieldCategory.selectedItem.toString()
                val transaction = Transaction(
                    type = type,
                    value = value,
                    date = date,
                    category = category
                )
                transactionDelegate.delegate(transaction)
            })
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun titleBy(type: Type): Int {

        if (type == Type.REVENUE) {
            return R.string.altera_receita
        }

        return R.string.altera_despesa
    }


    private fun convertToBigDecimal(valueText: String): BigDecimal {

        return try {
            BigDecimal(valueText)
        } catch (exception: NumberFormatException) {
            Toast.makeText(
                context, "Campo valor não foi preenchido ou preenchido sem números ",
                Toast.LENGTH_LONG
            ).show()
            BigDecimal.ZERO
        }
    }

    private fun configFieldCategory(type: Type) {

        val category = categoryBy(type)

        val adapter = ArrayAdapter.createFromResource(
            context,
            category, android.R.layout.simple_spinner_dropdown_item
        )

        fieldCategory.adapter = adapter
    }

    private fun categoryBy(type: Type): Int {
        if (type == Type.REVENUE) {
          return  R.array.categorias_de_receita
        }
         return   R.array.categorias_de_despesa
    }

    private fun configFieldDate() {

        val today = Calendar.getInstance()
        val year = today.get(Calendar.YEAR)
        val month = today.get(Calendar.MONTH)
        val day = today.get(Calendar.DAY_OF_MONTH)

        fieldDate.setText(today.formatToBR())

        fieldDate.setOnClickListener {
            DatePickerDialog(
                context,
                 { _, year, month, dayOfMonth ->

                    val dateSelected = Calendar.getInstance()

                    dateSelected.set(year, month, dayOfMonth)

                    fieldDate.setText(dateSelected.formatToBR())

                }, year, month, day
            ).show()
        }
    }

    private fun createLayout(): View {

        return LayoutInflater.from(context)
            .inflate(R.layout.form_transacao, viewGroup, false)
    }


}