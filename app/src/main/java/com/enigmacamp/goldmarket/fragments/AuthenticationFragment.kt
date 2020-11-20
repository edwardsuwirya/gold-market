package com.enigmacamp.goldmarket.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.enigmacamp.goldmarket.LoadingDialog
import com.enigmacamp.goldmarket.MainActivity
import com.enigmacamp.goldmarket.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [AuthenticationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AuthenticationFragment : AppBaseFragment() {
    // TODO: Rename and change types of parameters

    lateinit var okButton: Button
    lateinit var cancelButton: Button
    lateinit var loadingDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sourceView = arguments?.getInt("TRANSACTION_KEY")
        val title = arguments?.getString(MainActivity.TITLE_KEY)
        loadingDialog = LoadingDialog.build(requireContext())

        okButton = requireView().findViewById(R.id.auth_button)
        okButton.setOnClickListener {
            loadingDialog.show()
            GlobalScope.launch {
                delay(1000)
                loadingDialog.dismiss()
                findNavController().navigate(
                    R.id.action_home_fragment,
                    bundleOf("STATUS" to "OK", MainActivity.TITLE_KEY to title)
                )
            }

        }
        cancelButton = requireView().findViewById(R.id.cancel_button)
        cancelButton.setOnClickListener {
            findNavController().popBackStack(
                sourceView!!, false
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        hideActivityBar()
        return inflater.inflate(R.layout.fragment_authentication, container, false)
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = AuthenticationFragment()
    }
}