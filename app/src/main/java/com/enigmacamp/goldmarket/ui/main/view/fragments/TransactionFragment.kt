package com.enigmacamp.goldmarket.ui.main.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.enigmacamp.goldmarket.util.AppTextWatcher
import com.enigmacamp.goldmarket.ui.main.view.activity.MainActivity
import com.enigmacamp.goldmarket.R
import com.enigmacamp.goldmarket.ui.base.AppBaseFragment

// TODO: Rename parameter arguments, choose names that match

/**
 * A simple [Fragment] subclass.
 * Use the [TransactionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TransactionFragment : AppBaseFragment() {
    lateinit var gold_amount_input: String
    lateinit var gold_amount_gr: TextView
    lateinit var gold_amount_rp: TextView
    lateinit var price: TextView
    lateinit var totalPrice: TextView
    lateinit var totalTransaction: TextView
    lateinit var gold_amount: TextView
    lateinit var paymentButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val title = arguments?.getString(MainActivity.TITLE_KEY)
        requireActivity().title = title
        gold_amount_rp = requireView().findViewById(R.id.gold_amount_rp)
        gold_amount_input = "0"
        gold_amount_gr = requireView().findViewById(R.id.gold_amount_gr)
        gold_amount_gr.text = (gold_amount_input.toInt() / 9000000).toString()

        price = requireView().findViewById(R.id.price)
        totalPrice = requireView().findViewById(R.id.totalPrice)
        totalTransaction = requireView().findViewById(R.id.totalTransaction)
        gold_amount = requireView().findViewById(R.id.gold_amount)

        paymentButton = requireView().findViewById(R.id.payment_button)

        paymentButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_authentication_fragment,
                bundleOf(TRX_KEY to REQUEST_CODE, MainActivity.TITLE_KEY to title)
            )
        }
        gold_amount_rp.addTextChangedListener(
            AppTextWatcher {
                afterChanged = { s ->
                    var rupiah = s
                    price.text = "Rp" + rupiah
                    totalPrice.text = "Rp" + rupiah
                    totalTransaction.text = "Rp" + rupiah
                    gold_amount_input = s.toString()
                    gold_amount_gr.text = if (gold_amount_input.length > 0) {
                        "${"%.4f".format(gold_amount_input.toDouble() / 900000)}"
                    } else "0"

                    gold_amount.text = if (gold_amount_input.length > 0) {
                        (Math.floor(gold_amount_input.toDouble() / 900000)).toString() + " gr emas"
                    } else "0 gr emas"
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