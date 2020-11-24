package com.enigmacamp.goldmarket.ui.main.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.enigmacamp.goldmarket.R
import com.enigmacamp.goldmarket.data.model.AppState
import com.enigmacamp.goldmarket.data.model.Customer
import com.enigmacamp.goldmarket.databinding.FragmentHomeBinding
import com.enigmacamp.goldmarket.ui.LoadingDialog
import com.enigmacamp.goldmarket.ui.base.AppBaseFragment
import com.enigmacamp.goldmarket.ui.main.view.activity.MainActivity
import com.enigmacamp.goldmarket.ui.main.viewmodel.HomeFragmentViewModel
import com.enigmacamp.goldmarket.ui.main.viewmodel.HomeFragmentViewModelInjector


// TODO: Rename parameter arguments, choose names that match

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : AppBaseFragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentHomeBinding
    lateinit var loadingDialog: AlertDialog
    lateinit var viewmodel: HomeFragmentViewModel

    var goldBuyPrice = 0.0f
    var goldSellPrice = 0.0f

    private fun initUi() {
        loadingDialog = LoadingDialog.build(requireContext())
    }

    private fun initViewModel() {
        viewmodel = ViewModelProvider(this, HomeFragmentViewModelInjector.getFactory()).get(
            HomeFragmentViewModel::class.java
        )
    }

    private fun subscribe() {
        viewmodel.response.observe(requireActivity(), {
            when (it) {
                is AppState.Loading -> {
                    loadingDialog.show()
                }
                is AppState.Success -> {
                    loadingDialog.dismiss()
                }
                is AppState.Error -> {
                    loadingDialog.dismiss()
                }
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUi()
        initViewModel()
        subscribe()
        val title = arguments?.getString(MainActivity.TITLE_KEY)
        requireActivity().title = title
        val authCustomer = arguments?.getParcelable<Customer>(MainActivity.CUSTOMER_KEY)
        viewmodel.getCurrentGoldPriceAndCalculateCustomerBalance(authCustomer)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.apply {
            homeFragmentViewModel = viewmodel
            buyButton.setOnClickListener {
                findNavController().navigate(
                    R.id.action_homeFragment_to_transactionFragment,
                    bundleOf(
                        TRX_TYPE_KEY to TRX_BUY,
                        TRX_GOLD_PRICE to goldBuyPrice,
                        CUSTOMER_KEY to authCustomer,
                        MainActivity.TITLE_KEY to TRX_BUY
                    )
                )
            }

            sellButton.setOnClickListener {
                findNavController().navigate(
                    R.id.action_homeFragment_to_transactionFragment,
                    bundleOf(
                        TRX_TYPE_KEY to TRX_SELL,
                        TRX_GOLD_PRICE to goldSellPrice,
                        CUSTOMER_KEY to authCustomer,
                        MainActivity.TITLE_KEY to TRX_SELL
                    )
                )
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        showActivityBar()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        const val TRX_TYPE_KEY = "TRANSACTION_TYPE_KEY"
        const val TRX_BUY = "BELI"
        const val TRX_SELL = "JUAL"
        const val CUSTOMER_KEY = "CUSTOMER_KEY"
        const val TRX_GOLD_PRICE = "GOLD_PRICE_KEY"

        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}