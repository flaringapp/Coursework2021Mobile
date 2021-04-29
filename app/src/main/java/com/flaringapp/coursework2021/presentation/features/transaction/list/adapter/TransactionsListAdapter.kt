package com.flaringapp.coursework2021.presentation.features.transaction.list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flaringapp.coursework2021.app.common.clearAndAdd
import com.flaringapp.coursework2021.presentation.features.transaction.list.models.TransactionViewData

class TransactionsListAdapter: RecyclerView.Adapter<TransactionViewHolder>() {

    private val items: MutableList<TransactionViewData> = mutableListOf()

    fun setItems(items: List<TransactionViewData>) {
        this.items.clearAndAdd(items)
        notifyDataSetChanged()
    }

    fun addItem(item: TransactionViewData) {
        items.add(0, item)
        notifyItemInserted(0)
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        return TransactionViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(items[position])
    }
}