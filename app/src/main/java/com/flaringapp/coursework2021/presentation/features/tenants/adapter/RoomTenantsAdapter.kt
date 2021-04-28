package com.flaringapp.coursework2021.presentation.features.tenants.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flaringapp.coursework2021.app.common.Action
import com.flaringapp.coursework2021.app.common.clearAndAdd
import com.flaringapp.coursework2021.presentation.features.tenants.models.TenantViewData

class RoomTenantsAdapter(
    private val onAddTenant: Action,
    private val onRemoveTenant: (String) -> Unit
): RecyclerView.Adapter<RoomTenantsItemViewHolder>() {

    companion object {
        private const val VIEW_TYPE_TENANT = 1
        private const val VIEW_TYPE_ADD = 2
    }

    private val items: MutableList<TenantViewData> = mutableListOf()

    private var showAddTenant: Boolean = false

    fun setItems(items: List<TenantViewData>) {
        this.items.clearAndAdd(items)
        notifyDataSetChanged()
    }

    fun addItem(item: TenantViewData) {
        items += item
        notifyItemInserted(items.size)
    }

    fun removeItem(id: String) {
        val index = items.indexOfFirst { it.rentId == id }
            .takeIf { it >= 0 } ?: return

        items.removeAt(index)
        notifyItemRemoved(index)
    }

    fun setCanAdd(canAdd: Boolean) {
        if (this.showAddTenant == canAdd) return
        this.showAddTenant = canAdd
        if (canAdd) notifyItemInserted(items.size)
        else notifyItemRemoved(items.size)
    }

    override fun getItemCount(): Int {
        var count = items.size
        if (showAddTenant) count++
        return count
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            showAddTenant && position == items.size -> VIEW_TYPE_ADD
            else -> VIEW_TYPE_TENANT
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomTenantsItemViewHolder {
        return when (viewType) {
            VIEW_TYPE_TENANT -> RoomTenantViewHolder.create(parent)
            else -> RoomAddTenantViewHolder.create(parent)
        }
    }

    override fun onBindViewHolder(holder: RoomTenantsItemViewHolder, position: Int) {
        when (holder) {
            is RoomTenantViewHolder -> {
                holder.bind(items[position], onRemoveTenant)
            }
            is RoomAddTenantViewHolder -> {
                holder.bind(onAddTenant)
            }
        }
    }

    override fun onViewRecycled(holder: RoomTenantsItemViewHolder) {
        holder.release()
    }

}