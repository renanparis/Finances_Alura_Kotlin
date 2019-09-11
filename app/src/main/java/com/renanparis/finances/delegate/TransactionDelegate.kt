package com.renanparis.finances.delegate

import com.renanparis.finances.model.Transaction

interface TransactionDelegate {

    fun delegate(transaction: Transaction)
}