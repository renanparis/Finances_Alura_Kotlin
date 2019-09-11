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
class InsertFormRevenueDialog(
    private val viewGroup: ViewGroup,
    private val context: Context
) {

    private val viewCreated = createLayout()

    fun configDialog(transactionDelegate: TransactionDelegate) {

        configFieldDate()
        configFieldCategory()
        configFormRevenue(transactionDelegate)
    }

    private fun configFormRevenue(transactionDelegate: TransactionDelegate) {

        AlertDialog.Builder(context)
            .setTitle(R.string.adiciona_receita)
            .setView(viewCreated)
            .setPositiveButton("Adicionar", { _: DialogInterface, _: Int ->
                val valueText = viewCreated.form_transacao_valor.text.toString()
                val value = convertToBigDecimal(valueText)
                val dateText = viewCreated.form_transacao_data.text.toString()
                val date = dateText.convertToCalendar()
                val category = viewCreated.form_transacao_categoria.selectedItem.toString()
                val transaction = Transaction(
                    type = Type.REVENUE,
                    value = value,
                    date = date,
                    category = category
                )
                transactionDelegate.delegate(transaction)
            })
            .setNegativeButton("Cancelar", null)
            .show()
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

    private fun configFieldCategory() {
        val adapter = ArrayAdapter.createFromResource(
            context,
            R.array.categorias_de_receita, android.R.layout.simple_spinner_dropdown_item
        )

        viewCreated.form_transacao_categoria.adapter = adapter
    }

    private fun configFieldDate() {

        val today = Calendar.getInstance()
        val year = today.get(Calendar.YEAR)
        val month = today.get(Calendar.MONTH)
        val day = today.get(Calendar.DAY_OF_MONTH)

        viewCreated.form_transacao_data.setText(today.formatToBR())

        viewCreated.form_transacao_data.setOnClickListener {
            DatePickerDialog(
                context,
                DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->

                    val dateSelected = Calendar.getInstance()

                    dateSelected.set(year, month, dayOfMonth)

                    viewCreated.form_transacao_data.setText(dateSelected.formatToBR())

                }, year, month, day
            ).show()
        }
    }

    private fun createLayout(): View {

        return LayoutInflater.from(context)
            .inflate(R.layout.form_transacao, viewGroup, false)
    }


}