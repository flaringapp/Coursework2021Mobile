package com.flaringapp.coursework2021.presentation.features.transaction.list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flaringapp.coursework2021.databinding.ViewHolderTransactionBinding
import com.flaringapp.coursework2021.presentation.features.transaction.list.models.TransactionViewData
import com.flaringapp.coursework2021.presentation.utils.inflater

class TransactionViewHolder private constructor(
    private val binding: ViewHolderTransactionBinding
): RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) = TransactionViewHolder(
            ViewHolderTransactionBinding.inflate(parent.inflater, parent, false)
        )
    }

    fun bind(item: TransactionViewData) = with(binding) {
        textName.text = item.residentName
        textRoom.text = item.roomName
        textDateRange.text = item.dateRange
        textMessage.text = item.paymentMessage
        textAmount.text = item.amount
        textTransactionTime.text = item.transactionTime
    }


}