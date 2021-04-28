package com.flaringapp.coursework2021.presentation.features.tenants.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.flaringapp.coursework2021.app.common.clearAndAdd
import com.flaringapp.coursework2021.app.common.launchOnIO
import com.flaringapp.coursework2021.app.common.removeFirst
import com.flaringapp.coursework2021.app.common.withMainContext
import com.flaringapp.coursework2021.data.repository.residents.models.Resident
import com.flaringapp.coursework2021.data.repository.tenants.RentsRepository
import com.flaringapp.coursework2021.data.repository.tenants.models.Rent
import com.flaringapp.coursework2021.data.repository.tenants.models.Tenant
import com.flaringapp.coursework2021.data.text.TextProvider
import com.flaringapp.coursework2021.presentation.features.tenants.RoomTenantsParams
import com.flaringapp.coursework2021.presentation.features.tenants.models.TenantViewData
import com.flaringapp.coursework2021.presentation.utils.common.SingleLiveEvent

class TenantsModelImpl(
    private val repository: RentsRepository,
    private val textProvider: TextProvider
): TenantsModel() {

    override val tenantsData = MutableLiveData<List<TenantViewData>>()
    override val canAddTenantsData = MutableLiveData<Boolean>()

    override val openAddNewTenantData = MutableLiveData<String>()

    override val tenantAddedData = SingleLiveEvent<TenantViewData>()
    override val tenantRemovedData = SingleLiveEvent<String>()

    override val openConfirmRemoveTenantData = MutableLiveData<String>()

    private lateinit var roomId: String
    private lateinit var roomName: String

    private var maxTenants = 1

    private val rents: MutableList<Rent> = mutableListOf()

    private var pendingRemoveRentId: String? = null

    override fun init(params: RoomTenantsParams) {
        this.roomId = params.roomId
        this.roomName = params.roomName
        this.maxTenants = params.maxTenantsCount
    }

    override fun loadData() {
        loadRents()
    }

    override fun requestAddNewTenant() {
        if (rents.size >= maxTenants) return
        openAddNewTenantData.value = roomName
    }

    override fun addNewTenant(resident: Resident) {
        val tenant = Tenant(resident)
        addRent(tenant)
    }

    override fun removeTenant(id: String) {
        val tenant: Tenant = rents.find { it.id == id }?.tenant ?: return
        pendingRemoveRentId = id
        openConfirmRemoveTenantData.value = tenant.formatNameSurname(textProvider).toString()
    }

    override fun confirmRemoveTenant() {
        val rentId = pendingRemoveRentId ?: return
        val rent = rents.find { it.id == rentId } ?: return
        deleteRent(rent)
    }

    private fun loadRents() {
        viewModelScope.launchOnIO {
            val loadedRents = safeCall {
                repository.getRents(roomId)
            } ?: return@launchOnIO

            val viewData = loadedRents.map { it.toViewData() }

            withMainContext {
                rents.clearAndAdd(loadedRents)

                tenantsData.value = viewData
                updateCanAddTenants()
            }
        }
    }

    private fun addRent(tenant: Tenant) {
        viewModelScope.launchOnIO {
            val rent = safeCall {
                repository.addRent(roomId, tenant)
            } ?: return@launchOnIO

            val viewData = rent.toViewData()

            withMainContext {
                rents.add(rent)
                tenantAddedData.value = viewData
                updateCanAddTenants()
            }
        }
    }

    private fun deleteRent(rent: Rent) {
        viewModelScope.launchOnIO {
            safeCall { repository.removeRent(rent.id) } ?: return@launchOnIO

            withMainContext {
                rents.removeFirst { it.id == rent.id }
                tenantRemovedData.value = rent.id
                updateCanAddTenants()
            }
        }
    }

    private fun updateCanAddTenants() {
        canAddTenantsData.value = rents.size < maxTenants
    }

    private fun Rent.toViewData() = TenantViewData(
        id,
        textProvider.formatNameSurname(tenant.residentName, tenant.residentSurname)
    )
}