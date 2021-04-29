package com.flaringapp.coursework2021.presentation.features.tenants.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.flaringapp.coursework2021.app.common.clearAndAdd
import com.flaringapp.coursework2021.app.common.launchOnIO
import com.flaringapp.coursework2021.app.common.removeFirst
import com.flaringapp.coursework2021.app.common.withMainContext
import com.flaringapp.coursework2021.data.repository.residents.models.Resident
import com.flaringapp.coursework2021.data.repository.tenants.RentalsRepository
import com.flaringapp.coursework2021.data.repository.tenants.models.AddRental
import com.flaringapp.coursework2021.data.repository.tenants.models.Rental
import com.flaringapp.coursework2021.data.repository.tenants.models.Tenant
import com.flaringapp.coursework2021.data.text.TextProvider
import com.flaringapp.coursework2021.presentation.features.tenants.RoomTenantsParams
import com.flaringapp.coursework2021.presentation.features.tenants.models.TenantViewData
import com.flaringapp.coursework2021.presentation.utils.common.SingleLiveEvent

class TenantsModelImpl(
    private val repository: RentalsRepository,
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

    private val rentals: MutableList<Rental> = mutableListOf()

    private var pendingRemoveRentId: String? = null

    override fun init(params: RoomTenantsParams) {
        this.roomId = params.roomId
        this.roomName = params.roomName
        this.maxTenants = params.maxTenantsCount
    }

    override fun loadData() {
        loadRentals()
    }

    override fun requestAddNewTenant() {
        if (rentals.size >= maxTenants) return
        openAddNewTenantData.value = roomName
    }

    override fun addNewTenant(resident: Resident) {
        val tenant = Tenant(resident)
        addRental(tenant)
    }

    override fun removeTenant(id: String) {
        val tenant: Tenant = rentals.find { it.id == id }?.tenant ?: return
        pendingRemoveRentId = id
        openConfirmRemoveTenantData.value = tenant.formatNameSurname(textProvider).toString()
    }

    override fun confirmRemoveTenant() {
        val rentId = pendingRemoveRentId ?: return
        val rent = rentals.find { it.id == rentId } ?: return
        deleteRent(rent)
    }

    private fun loadRentals() {
        viewModelScope.launchOnIO {
            val loadedRentals = safeCall {
                repository.getRentalsByRoom(roomId)
            } ?: return@launchOnIO

            val viewData = loadedRentals.map { it.toViewData() }

            withMainContext {
                rentals.clearAndAdd(loadedRentals)

                tenantsData.value = viewData
                updateCanAddTenants()
            }
        }
    }

    private fun addRental(tenant: Tenant) {
        viewModelScope.launchOnIO {
            val addRental = AddRental(roomId, tenant.residentId)
            val rental = safeCall {
                repository.addRental(addRental)
            } ?: return@launchOnIO

            val viewData = rental.toViewData()

            withMainContext {
                rentals.add(rental)
                tenantAddedData.value = viewData
                updateCanAddTenants()
            }
        }
    }

    private fun deleteRent(rental: Rental) {
        viewModelScope.launchOnIO {
            safeCall { repository.removeRental(rental.id) } ?: return@launchOnIO

            withMainContext {
                rentals.removeFirst { it.id == rental.id }
                tenantRemovedData.value = rental.id
                updateCanAddTenants()
            }
        }
    }

    private fun updateCanAddTenants() {
        canAddTenantsData.value = rentals.size < maxTenants
    }

    private fun Rental.toViewData() = TenantViewData(
        id,
        textProvider.formatNameSurname(tenant.residentName, tenant.residentSurname)
    )
}