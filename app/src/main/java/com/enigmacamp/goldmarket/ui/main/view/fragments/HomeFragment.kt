package com.enigmacamp.goldmarket.ui.main.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.enigmacamp.goldmarket.R
import com.enigmacamp.goldmarket.data.model.CustomerBalance
import com.enigmacamp.goldmarket.ui.base.AppBaseFragment
import com.enigmacamp.goldmarket.ui.main.view.activity.MainActivity


// TODO: Rename parameter arguments, choose names that match

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : AppBaseFragment() {
    // TODO: Rename and change types of parameters
    lateinit var goldAmount: TextView
    lateinit var goldAmountRp: TextView
    lateinit var buyButton: Button
    lateinit var sellButton: Button

    private fun initUi() {
        goldAmount = requireView().findViewById(R.id.label_user_gold_amount_gram)
        goldAmountRp = requireView().findViewById(R.id.user_gold_amount_rp)
        buyButton = requireView().findViewById(R.id.btn_beli)
        sellButton = requireView().findViewById(R.id.btn_jual)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUi()
        val title = arguments?.getString(MainActivity.TITLE_KEY)
        requireActivity().title = title
        val user_gold_amount = arguments?.getParcelable<CustomerBalance>(MainActivity.BALANCE_KEY)

        goldAmount.text = user_gold_amount?.goldInGram.toString() + " gram"
        goldAmountRp.text = "Rp" + (user_gold_amount?.goldInGram ?: 0 * 900000).toString()

        buyButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_homeFragment_to_transactionFragment,
                bundleOf(TRX_TYPE_KEY to TRX_BUY, MainActivity.TITLE_KEY to TRX_BUY)
            )
        }

        sellButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_homeFragment_to_transactionFragment,
                bundleOf(TRX_TYPE_KEY to TRX_SELL, MainActivity.TITLE_KEY to TRX_SELL)
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        showActivityBar()
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        const val TRX_TYPE_KEY = "TRANSACTION_TYPE_KEY"
        const val TRX_BUY = "BELI"
        const val TRX_SELL = "JUAL"

        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}