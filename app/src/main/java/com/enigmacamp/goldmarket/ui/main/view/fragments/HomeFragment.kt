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
import com.enigmacamp.goldmarket.data.model.CustomerBalance
import com.enigmacamp.goldmarket.ui.main.view.activity.MainActivity
import com.enigmacamp.goldmarket.R
import com.enigmacamp.goldmarket.ui.base.AppBaseFragment


// TODO: Rename parameter arguments, choose names that match

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : AppBaseFragment() {
    // TODO: Rename and change types of parameters
    lateinit var gold_amount: TextView
    lateinit var gold_amount_rp: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val title = arguments?.getString(MainActivity.TITLE_KEY)
        requireActivity().title = title
        val user_gold_amount = arguments?.getParcelable<CustomerBalance>(MainActivity.BALANCE_KEY)
        gold_amount = requireView().findViewById(R.id.label_user_gold_amount_gram)
        gold_amount.text = user_gold_amount?.goldInGram.toString() + " gram"

        gold_amount_rp = view.findViewById(R.id.user_gold_amount_rp)
        gold_amount_rp.text = "Rp" + (user_gold_amount?.goldInGram ?: 0 * 900000).toString()

        val buyButton: Button = view.findViewById<View>(R.id.btn_beli) as Button
        buyButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                findNavController().navigate(
                    R.id.action_homeFragment_to_transactionFragment,
                    bundleOf(TRX_TYPE_KEY to TRX_BUY, MainActivity.TITLE_KEY to TRX_BUY)
                )
            }
        })
        val sellButton: Button = view.findViewById<View>(R.id.btn_jual) as Button
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