package com.flaringapp.coursework2021.presentation.features.transaction.create.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.flaringapp.coursework2021.R
import com.flaringapp.coursework2021.app.common.launchOnIO
import com.flaringapp.coursework2021.app.common.withMainContext
import com.flaringapp.coursework2021.data.repository.profile.models.ManagerInfo
import com.flaringapp.coursework2021.data.repository.residents.ResidentsRepository
import com.flaringapp.coursework2021.data.repository.residents.models.Resident
import com.flaringapp.coursework2021.data.repository.tenants.RentalsRepository
import com.flaringapp.coursework2021.data.repository.tenants.models.Rental
import com.flaringapp.coursework2021.data.repository.transactions.TransactionsRepository
import com.flaringapp.coursework2021.data.text.TextProvider
import com.flaringapp.coursework2021.presentation.features.transaction.create.models.*
import com.flaringapp.coursework2021.presentation.utils.common.SingleLiveEvent
import com.flaringapp.coursework2021.presentation.utils.startLoadingTask
import com.flaringapp.coursework2021.presentation.utils.startLoadingTaskInverted
import kotlinx.coroutines.Job

class CreateTransactionModelImpl(
    managerInfo: ManagerInfo,
    private val residentsRepository: ResidentsRepository,
    private val rentalsRepository: RentalsRepository,
    private val transactionsRepository: TransactionsRepository,
    private val textProvider: TextProvider,
) : CreateTransactionModel() {

    override val residentsData = MutableLiveData<TransactionSelectResidentViewData>()
    override val rentalsData = MutableLiveData<TransactionSelectRentalViewData>()
    override val monthsCountData = MutableLiveData<Int>()

    override val residentErrorData = SingleLiveEvent<Int?>()
    override val rentalErrorData = SingleLiveEvent<Int?>()

    override val residentsAvailableData = MutableLiveData(false)
    override val rentalsAvailableData = MutableLiveData(false)

    override val priceData = MutableLiveData<CharSequence>()

    override val loadingData = MutableLiveData(false)

    override val closeScreenData = SingleLiveEvent<Unit>()

    private val buildingId: String = managerInfo.buildingId

    private var residents: List<Resident> = emptyList()
    private var rentals: List<Rental> = emptyList()

    private var editor = TransactionEditableData()

    private var loadedRentalsResidentId: String? = null
    private var loadRentalsJob: Job? = null

    init {
        editor.setup()

        loadResidents()

        updatePrice()
    }

    override fun handleResidentChanged(id: String) {
        if (editor.residentId == id) return

        val resident = residents.find { it.id == id } ?: return
        editor.residentId = id
        editor.residentName = resident.name

        editor.rentalId = null
        editor.rentalName = null

        rentals = emptyList()

        rentalsData.value = TransactionSelectRentalViewData()
        rentalsAvailableData.value = false

        loadRentals(id)

        updatePrice()
    }

    override fun handleRentalChanged(id: String) {
        val rental = rentals.find { it.id == id } ?: return
        editor.rentalId = id
        editor.rentalName = rental.room.roomName

        updatePrice()
    }

    override fun handleMonthCountChanged(monthsCount: Int) {
        editor.monthsCount = monthsCount
        updatePrice()
    }

    override fun createTransaction() {
        if (!isValid()) return
        performCreateTransaction()
    }

    private fun TransactionEditableData.setup() {
        monthsCountData.value = monthsCount
    }

    private fun loadResidents() {
        viewModelScope.startLoadingTaskInverted(residentsAvailableData) {
            val loadedResidents = safeCall {
                residentsRepository.getResidents(buildingId)
            }?: return@startLoadingTaskInverted

            val residentsViewData = loadedResidents.map { it.toViewData() }
            val selectionViewData = TransactionSelectResidentViewData(
                residentsViewData,
                editor.residentName
            )

            withMainContext {
                residents = loadedResidents
                residentsData.value = selectionViewData
            }
        }
    }

    private fun loadRentals(residentId: String) {
        if (loadedRentalsResidentId == residentId) return
        loadRentalsJob?.cancel()

        loadRentalsJob = viewModelScope.launchOnIO {
            val loadedRentals = safeCall {
                rentalsRepository.getRentalsByResident(residentId)
            }?: return@launchOnIO

            val rentalsViewData = loadedRentals.map { it.toViewData() }
            val selectionViewData = TransactionSelectRentalViewData(
                rentalsViewData,
                editor.rentalName
            )

            withMainContext {
                loadedRentalsResidentId = residentId
                rentals = loadedRentals
                rentalsData.value = selectionViewData
                rentalsAvailableData.value = true
            }
        }
    }

    private fun performCreateTransaction() {
        viewModelScope.startLoadingTask(loadingData) {
            val transaction = editor.toAddTransaction(buildingId)
            safeCall {
                transactionsRepository.makeTransaction(transaction)
            } ?: return@startLoadingTask

            withMainContext {
                closeScreenData.value = Unit
            }
        }
    }

    private fun updatePrice() {
        val price = resolveRentalPrice()
        val text =
            if (price == null) textProvider.emptyPriceText()
            else textProvider.formatPrice(price)
        priceData.value = text
    }

    private fun resolveRentalPrice(): Int? {
        val rentalId = editor.rentalId ?: return null
        val rental = rentals.find { it.id == rentalId } ?: return null
        return calculateRentalPrice(rental, editor.monthsCount)
    }

    private fun calculateRentalPrice(rental: Rental, monthsCount: Int): Int {
        return rental.room.roomPrice * monthsCount
    }

    private fun isValid(): Boolean {
        when {
            editor.residentId == null -> {
                residentErrorData.value = R.string.error_payment_empty_resident
            }
            editor.rentalId == null -> {
                residentErrorData.value = R.string.error_payment_empty_rental
            }
            else -> return true
        }
        return false
    }

    private fun Resident.toViewData() = TransactionResidentViewData(
        id,
        formatNameSurname(textProvider)
    )

    private fun Rental.toViewData() = TransactionRentalViewData(
        id,
        room.roomName
    )

}