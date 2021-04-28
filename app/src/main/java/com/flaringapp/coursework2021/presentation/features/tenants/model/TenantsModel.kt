package com.flaringapp.coursework2021.presentation.features.tenants.model

import androidx.lifecycle.LiveData
import com.flaringapp.coursework2021.data.repository.residents.models.Resident
import com.flaringapp.coursework2021.data.repository.tenants.models.Tenant
import com.flaringapp.coursework2021.presentation.base.BaseViewModel
import com.flaringapp.coursework2021.presentation.features.tenants.RoomTenantsParams
import com.flaringapp.coursework2021.presentation.features.tenants.models.TenantViewData

abstract class TenantsModel: BaseViewModel() {

    abstract val tenantsData: LiveData<List<TenantViewData>>
    abstract val canAddTenantsData: LiveData<Boolean>

    abstract val tenantAddedData: LiveData<TenantViewData>
    abstract val tenantRemovedData: LiveData<String>

    abstract val openAddNewTenantData: LiveData<String>

    abstract val openConfirmRemoveTenantData: LiveData<String>

    abstract fun init(params: RoomTenantsParams)
    abstract fun loadData()

    abstract fun requestAddNewTenant()
    abstract fun addNewTenant(resident: Resident)

    abstract fun removeTenant(id: String)
    abstract fun confirmRemoveTenant()

}