package com.flaringapp.coursework2021.presentation.features.dialogs.options.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flaringapp.coursework2021.presentation.features.dialogs.options.viewdata.OptionViewData

class OptionsAdapter(
    private val header: CharSequence?,
    private val message: CharSequence?,
    private val items: List<OptionViewData>,
    private val clickBlock: (String) -> Unit
) : RecyclerView.Adapter<OptionBaseViewHolder>() {

    companion object {
        private const val TYPE_HEADER = 1
        private const val TYPE_MESSAGE = 2
        private const val TYPE_OPTION = 3
    }

    private val withHeader
        get() = header != null

    private val withMessage
        get() = message != null

    private val messagePosition: Int
        get() {
            return if (withHeader) 1
            else 0
        }

    private val itemsOffset: Int
        get() {
            var offset = 0
            if (withHeader) offset++
            if (withMessage) offset++

            return offset
        }

    private fun getItemForPosition(position: Int) = items[position - itemsOffset]

    override fun getItemCount(): Int = items.size + itemsOffset

    override fun getItemViewType(position: Int): Int {
        return if (withHeader && position == 0) TYPE_HEADER
        else if (withMessage && position == messagePosition) TYPE_MESSAGE
        else TYPE_OPTION
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionBaseViewHolder {
        return when (viewType) {
            TYPE_HEADER -> OptionHeaderViewHolder.create(parent)
            TYPE_MESSAGE -> OptionMessageViewHolder.create(parent)
            TYPE_OPTION -> OptionViewHolder.create(parent)
            else -> OptionViewHolder.create(parent)
        }
    }

    override fun onBindViewHolder(holder: OptionBaseViewHolder, position: Int) {
        when (holder) {
            is OptionHeaderViewHolder -> header?.let { holder.bind(it) }
            is OptionMessageViewHolder -> message?.let { holder.bind(it) }
            is OptionViewHolder -> holder.bind(getItemForPosition(position), clickBlock)
        }
    }

    override fun onViewRecycled(holder: OptionBaseViewHolder) {
        when (holder) {
            is OptionViewHolder -> holder.release()
        }
        super.onViewRecycled(holder)
    }
}