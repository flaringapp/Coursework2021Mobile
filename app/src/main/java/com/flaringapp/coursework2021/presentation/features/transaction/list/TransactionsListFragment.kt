package com.flaringapp.coursework2021.presentation.features.transaction.list

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.flaringapp.coursework2021.R
import com.flaringapp.coursework2021.databinding.FragmentTransactionsListBinding
import com.flaringapp.coursework2021.presentation.base.ModelledFragment
import com.flaringapp.coursework2021.presentation.features.transaction.list.adapter.TransactionsListAdapter
import com.flaringapp.coursework2021.presentation.features.transaction.list.model.TransactionsModel
import com.flaringapp.coursework2021.presentation.utils.postScrollToTop
import com.flaringapp.coursework2021.presentation.utils.recycler.DividerItemDecoration
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TransactionsListFragment : ModelledFragment(R.layout.fragment_transactions_list) {

    override val model: TransactionsModel by viewModel()

    private val binding: FragmentTransactionsListBinding by viewBinding(
        FragmentTransactionsListBinding::bind
    )

    override fun initViews() = with(binding) {
        recyclerTransactions.layoutManager = LinearLayoutManager(requireContext())
        recyclerTransactions.adapter = TransactionsListAdapter()
        recyclerTransactions.addItemDecoration(
            DividerItemDecoration(requireContext(), R.drawable.bg_separator)
        )

        buttonNewTransaction.setOnClickListener {
            model.addNewTransaction()
        }
    }

    override fun observeModel() = with(model) {
        transactionsData.observe(viewLifecycleOwner) { transactions ->
            adapterAction { setItems(transactions) }
        }
        addTransactionData.observe(viewLifecycleOwner) { transaction ->
            adapterAction { addItem(transaction) }
            binding.recyclerTransactions.postScrollToTop()
        }

        openNewTransactionData.observe(viewLifecycleOwner) {
            openCreateTransactionScreen()
        }
    }

    private fun <T> adapterAction(action: TransactionsListAdapter.() -> T): T {
        return (binding.recyclerTransactions.adapter as TransactionsListAdapter).action()
    }

    private fun openCreateTransactionScreen() {
        findNavController().navigate(R.id.action_payments_list_to_create_payment)
    }

}