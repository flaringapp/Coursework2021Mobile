package com.flaringapp.coursework2021.presentation.features.common.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flaringapp.coursework2021.app.common.clearAndAdd

abstract class MutableListAdapter<
        IVH : MutableListItemViewHolder,
        AVH : MutableListAddItemViewHolder,
        I : MutableListItem>(
    private val isEditable: Boolean
) : RecyclerView.Adapter<MutableListViewHolder>() {

    companion object {
        private const val VIEW_TYPE_ITEM = 1
        private const val VIEW_TYPE_ADD_ITEM = 2
    }

    private val items: MutableList<I> = mutableListOf()

    fun setItems(items: List<I>) {
        this.items.clearAndAdd(items)
        notifyDataSetChanged()
    }

    fun addNewItem(item: I) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun updateItem(item: I) {
        val index = indexIfItem(item.id) ?: run {
            addNewItem(item)
            return
        }
        items[index] = item
        notifyItemChanged(index)
    }

    fun removeItem(id: String) {
        val index = indexIfItem(id) ?: return
        items.removeAt(index)
        notifyItemRemoved(index)
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return when {
            isEditable && position == items.size -> VIEW_TYPE_ADD_ITEM
            else -> VIEW_TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MutableListViewHolder {
        return when (viewType) {
            VIEW_TYPE_ITEM -> createItemViewHolder(parent)
            VIEW_TYPE_ADD_ITEM -> createAddItemViewHolder(parent)
            else -> throw IllegalStateException("Unsupported mutable list view type: $viewType")
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: MutableListViewHolder, position: Int) {
        when (holder) {
            is MutableListItemViewHolder -> bindItemViewHolder(holder as IVH, position)
            is MutableListAddItemViewHolder -> bindAddItemViewHolder(holder as AVH, position)
        }
    }

    override fun onViewRecycled(holder: MutableListViewHolder) {
        holder.release()
    }

    protected abstract fun createItemViewHolder(parent: ViewGroup): IVH

    protected abstract fun createAddItemViewHolder(parent: ViewGroup): AVH

    protected abstract fun bindItemViewHolder(holder: IVH, position: Int)

    protected abstract fun bindAddItemViewHolder(holder: AVH, position: Int)

    private fun indexIfItem(id: String): Int? {
        return items.indexOfFirst { it.id == id }
            .takeIf { it >= 0 }
    }

}