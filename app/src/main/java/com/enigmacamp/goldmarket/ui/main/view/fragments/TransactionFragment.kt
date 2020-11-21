package com.enigmacamp.goldmarket.ui.main.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.enigmacamp.goldmarket.R
import com.enigmacamp.goldmarket.ui.base.AppBaseFragment
import com.enigmacamp.goldmarket.ui.main.view.activity.MainActivity
import com.enigmacamp.goldmarket.ui.main.viewmodel.ProfileFragmentViewModel
import com.enigmacamp.goldmarket.ui.main.viewmodel.TransactionFragmentViewModel
import com.enigmacamp.goldmarket.util.AppTextWatcher

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

    lateinit var viewModel: TransactionFragmentViewModel

    private fun initUi() {
        goldAmountRp = requireView().findViewById(R.id.gold_amount_rp)
        goldAmountGr = requireView().findViewById(R.id.gold_amount_gr)
        price = requireView().findViewById(R.id.price)
        totalPrice = requireView().findViewById(R.id.totalPrice)
        totalTransaction = requireView().findViewById(R.id.totalTransaction)
        goldAmount = requireView().findViewById(R.id.gold_amount)
        paymentButton = requireView().findViewById(R.id.payment_button)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(TransactionFragmentViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUi()
        initViewModel()
        val title = arguments?.getString(MainActivity.TITLE_KEY)
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
                    price.text = "Rp ${rupiah}"
                    totalPrice.text = "Rp ${rupiah}"
                    totalTransaction.text = "Rp ${rupiah}"
                    viewModel.sGoldAmount = rupiah
                    viewModel.calculateGold()
                    goldAmountGr.text = viewModel.sGoldAmount
                    goldAmount.text = "${viewModel.sGoldAmount} gr emas "
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