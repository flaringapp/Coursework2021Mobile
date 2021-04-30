package com.flaringapp.coursework2021.presentation.features.transaction.list.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.flaringapp.coursework2021.R
import com.flaringapp.coursework2021.app.common.clearAndAdd
import com.flaringapp.coursework2021.app.common.collectOn
import com.flaringapp.coursework2021.app.common.launchOnIO
import com.flaringapp.coursework2021.app.common.withMainContext
import com.flaringapp.coursework2021.data.repository.entity.models.RoomType
import com.flaringapp.coursework2021.data.repository.profile.models.Profile
import com.flaringapp.coursework2021.data.repository.transactions.TransactionsRepository
import com.flaringapp.coursework2021.data.repository.transactions.models.Transaction
import com.flaringapp.coursework2021.data.repository.transactions.storage.TransactionsStorage
import com.flaringapp.coursework2021.data.text.TextProvider
import com.flaringapp.coursework2021.presentation.features.transaction.list.models.TransactionViewData
import com.flaringapp.coursework2021.presentation.utils.common.SingleLiveEvent
import com.flaringapp.coursework2021.presentation.utils.valueIfHasObservers
import java.time.LocalDate

class TransactionModelImpl(
    private val profile: Profile,
    private val repository: TransactionsRepository,
    storage: TransactionsStorage,
    private val textProvider: TextProvider
) : TransactionsModel() {

    override val transactionsData = MutableLiveData<List<TransactionViewData>>()
    override val addTransactionData = SingleLiveEvent<TransactionViewData>()

    override val openNewTransactionData = SingleLiveEvent<Unit>()

    private val transactions: MutableList<Transaction> = mutableListOf()

    init {
        loadTransactions()

        // TODO refactor general list validation
        storage.addTransactionFlow.collectOn(viewModelScope) { addedTransaction ->
            transactions.add(0, addedTransaction)
            addTransactionData.valueIfHasObservers = addedTransaction.toViewData()

            transactionsData.value = transactions.map { it.toViewData() }
        }
    }

    override fun addNewTransaction() {
        openNewTransactionData.value = Unit
    }

    private fun loadTransactions() {
        viewModelScope.launchOnIO {
            val loadedTransactions = safeCall {
                repository.getTransactions(profile.id)
            } ?: return@launchOnIO

            val viewData = loadedTransactions.map { it.toViewData() }

            withMainContext {
                transactions.clearAndAdd(loadedTransactions)
                transactionsData.value = viewData
            }
        }
    }

    private fun Transaction.toViewData() = TransactionViewData(
        id,
        resident?.formatNameSurname(textProvider)  ?: textProvider.noName(),
        room?.roomName ?: textProvider.noRoomName(),
        formatDateRange(dateFrom, dateTo),
        formatPaymentMessage(room?.roomType ?: RoomType.Unknown),
        textProvider.formatPrice(amount),
        textProvider.formatDateTime(timeCreated),
    )

    private fun formatDateRange(from: LocalDate, to: LocalDate): CharSequence {
        return "${textProvider.formatDate(from)} - ${textProvider.formatDate(to)}"
    }

    private fun formatPaymentMessage(type: RoomType): CharSequence {
        return textProvider.getText(
            when (type) {
                RoomType.Private -> R.string.message_payment_private_room
                RoomType.OpenSpace -> R.string.message_payment_open_space
                RoomType.Unknown -> R.string.message_payment_unknown
            }
        )
    }
}