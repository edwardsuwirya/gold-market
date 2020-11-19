package com.enigmacamp.goldmarket

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.enigmacamp.goldmarket.fragments.HistoryFragment
import com.enigmacamp.goldmarket.fragments.HomeFragment
import com.enigmacamp.goldmarket.fragments.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_profile.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    private var name = ""
    private var gold_amount = 0

    lateinit var authCustomer: Customer
    lateinit var customerBalance: CustomerBalance

    val TAG = "MainActivity"

    lateinit var homeFragment: Fragment
    lateinit var historyFragment: Fragment
    lateinit var profileFragment: ProfileFragment

    companion object {
        const val PROFILE_KEY = "profile_key"
        const val BALANCE_KEY = "balance_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        authCustomer = intent.getParcelableExtra(SignInActivity.INTENT_AUTH_CUSTOMER_KEY)
        name = "${authCustomer.firstName} ${authCustomer.lastName}"
        Log.d(TAG, name)

        customerBalance = intent.getParcelableExtra(SignInActivity.INTENT_CUSTOMER_BALANCE)
        Log.d(TAG, customerBalance.goldInGram.toString())

        navController = Navigation.findNavController(this, R.id.nav_fragment)
        navController.setGraph(navController.graph, bundleOf(BALANCE_KEY to customerBalance))

        homeFragment = HomeFragment()
        historyFragment = HistoryFragment()
        profileFragment = ProfileFragment()

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ic_home -> navController.navigate(
                    R.id.homeFragment,
                    bundleOf(BALANCE_KEY to customerBalance)
                )
                R.id.ic_history -> navController.navigate(R.id.historyFragment)
                R.id.ic_profile -> navController.navigate(
                    R.id.profileFragment,
                    bundleOf(PROFILE_KEY to authCustomer)
                )
            }
            true
        }
    }
}