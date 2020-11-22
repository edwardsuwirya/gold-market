package com.enigmacamp.goldmarket.ui.main.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.enigmacamp.goldmarket.R
import com.enigmacamp.goldmarket.data.model.AppState
import com.enigmacamp.goldmarket.data.model.Customer
import com.enigmacamp.goldmarket.ui.LoadingDialog
import com.enigmacamp.goldmarket.ui.base.AppBaseFragment
import com.enigmacamp.goldmarket.ui.main.view.activity.MainActivity
import com.enigmacamp.goldmarket.ui.main.viewmodel.ProfileFragmentViewModel
import com.enigmacamp.goldmarket.ui.main.viewmodel.TransactionFragmentViewModel
import com.enigmacamp.goldmarket.ui.main.viewmodel.TransactionFragmentViewModelInjector
import com.enigmacamp.goldmarket.util.AppTextWatcher
import java.text.DecimalFormat

// TODO: Rename parameter arguments, choose names that match

/**
 * A simple [Fragment] subclass.
 * Use the [TransactionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TransactionFragment : AppBaseFragment() {
    lateinit var goldAmountGr: TextView
    lateinit var goldAmountRp: TextView
    lateinit var price: TextView
    lateinit var totalPrice: TextView
    lateinit var totalTransaction: TextView
    lateinit var goldAmount: TextView
    lateinit var paymentButton: Button

    lateinit var loadingDialog: AlertDialog

    lateinit var viewModel: TransactionFragmentViewModel

    val numberFormat = DecimalFormat("Rp #,###.00")

    private fun initUi() {
        goldAmountRp = requireView().findViewById(R.id.gold_amount_rp)
        goldAmountGr = requireView().findViewById(R.id.gold_amount_gr)
        price = requireView().findViewById(R.id.price)
        totalPrice = requireView().findViewById(R.id.totalPrice)
        totalTransaction = requireView().findViewById(R.id.totalTransaction)
        goldAmount = requireView().findViewById(R.id.gold_amount)
        paymentButton = requireView().findViewById(R.id.payment_button)
        loadingDialog = LoadingDialog.build(requireContext())
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, TransactionFragmentViewModelInjector.getFactory()).get(
            TransactionFragmentViewModel::class.java
        )
    }

    private fun subscribe() {
        viewModel.goldAmountDeal.observe(requireActivity(), {
            val priceInRp = numberFormat.format(it.first)
            price.text = priceInRp
            totalPrice.text = priceInRp
            totalTransaction.text = priceInRp
            goldAmountGr.text = it.second
            goldAmount.text = "${it.second} gr emas "
        })
        viewModel.responseTransaction.observe(requireActivity(), {
            when (it) {
                is AppState.Loading -> loadingDialog.show()
                is AppState.Success -> {
                    loadingDialog.dismiss()
                    findNavController().navigate(
                        R.id.action_home_fragment,
                        bundleOf(MainActivity.CUSTOMER_KEY to viewModel.customer)
                    )
                }
            }
        })
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>("STATUS")
            ?.observe(
                viewLifecycleOwner
            ) { result ->
                if (result == "OK") {
                    viewModel.submitTransaction()
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUi()
        initViewModel()
        subscribe()
        val title = arguments?.getString(MainActivity.TITLE_KEY)
        viewModel.transactionType = title ?: ""
        viewModel.customer = arguments?.getParcelable(HomeFragment.CUSTOMER_KEY)

        viewModel.setPrice(arguments?.getFloat(HomeFragment.TRX_GOLD_PRICE) ?: 0.0f)
        requireActivity().title = title

        goldAmountGr.text = viewModel.goldAmount.toString()

        paymentButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_authentication_fragment,
                bundleOf(TRX_KEY to REQUEST_CODE, MainActivity.TITLE_KEY to title)
            )
        }
        goldAmountRp.addTextChangedListener(
            AppTextWatcher {
                afterChanged = { s ->
                    var rupiah = if (s.isNullOrBlank()) "0.0" else s.toString()
                    viewModel.requestTransaction(rupiah)
                }
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        showActivityBar()
        return inflater.inflate(R.layout.fragment_transaction, container, false)
    }

    companion object {
        const val TRX_KEY = "TRANSACTION_KEY"
        const val REQUEST_CODE = R.id.transactionFragment

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = TransactionFragment()
    }
}