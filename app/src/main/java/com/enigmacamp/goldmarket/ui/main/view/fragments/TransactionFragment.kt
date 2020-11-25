package com.enigmacamp.goldmarket.ui.main.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.enigmacamp.goldmarket.R
import com.enigmacamp.goldmarket.data.model.AppState
import com.enigmacamp.goldmarket.databinding.FragmentProfileBinding
import com.enigmacamp.goldmarket.databinding.FragmentTransactionBinding
import com.enigmacamp.goldmarket.ui.LoadingDialog
import com.enigmacamp.goldmarket.ui.base.AppBaseFragment
import com.enigmacamp.goldmarket.ui.main.view.activity.MainActivity
import com.enigmacamp.goldmarket.ui.main.viewmodel.AuthenticationViewModel
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
    val TAG = TransactionFragment::class.qualifiedName
    private lateinit var binding: FragmentTransactionBinding
    lateinit var loadingDialog: AlertDialog

    lateinit var viewModel: TransactionFragmentViewModel
    lateinit var authViewModel: AuthenticationViewModel

    private fun initUi() {
        loadingDialog = LoadingDialog.build(requireContext())
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, TransactionFragmentViewModelInjector.getFactory()).get(
            TransactionFragmentViewModel::class.java
        )
        authViewModel =
            ViewModelProvider(requireActivity()).get(AuthenticationViewModel::class.java)
    }

    private fun subscribe() {
        viewModel.responseTransaction.observe(requireActivity(), {
            when (it) {
                is AppState.Loading -> loadingDialog.show()
                is AppState.Success -> {
                    loadingDialog.dismiss()
                    authViewModel.clearStatus()
                    findNavController().navigate(
                        R.id.action_home_fragment,
                        bundleOf(MainActivity.CUSTOMER_KEY to viewModel.customer)
                    )
                }
                is AppState.Error -> {
                    loadingDialog.dismiss()
                    authViewModel.clearStatus()
                }
            }
        })
        Log.d(TAG, authViewModel.status.toString())
        if (authViewModel.status[REQUEST_CODE] == "OK") {
            viewModel.submitTransaction()
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

        binding.lifecycleOwner = viewLifecycleOwner
        binding.apply {
            binding.transactionFragmentViewModel = viewModel
            paymentButton.setOnClickListener {
                findNavController().navigate(
                    R.id.action_authentication_fragment,
                    bundleOf(TRX_KEY to REQUEST_CODE, MainActivity.TITLE_KEY to title)
                )
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        showActivityBar()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_transaction, container, false)
        return binding.root
    }

    companion object {
        const val TRX_KEY = "TRANSACTION_KEY"
        const val REQUEST_CODE = R.id.transactionFragment

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = TransactionFragment()
    }
}