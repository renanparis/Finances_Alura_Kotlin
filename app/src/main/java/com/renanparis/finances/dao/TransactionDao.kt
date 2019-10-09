package com.renanparis.finances.dao

import com.renanparis.finances.model.Transaction

class TransactionDao {

    val transactions: List<Transaction> = Companion.transactions

    companion object{

       private val  transactions: MutableList<Transaction> = mutableListOf()
    }

    fun add (transaction: Transaction){
        Companion.transactions.add(transaction)
    }

    fun remove(position: Int){

        Companion.transactions.removeAt(position)
    }


    fun update(transaction: Transaction, position: Int){
        Companion.transactions[position] = transaction
    }
}