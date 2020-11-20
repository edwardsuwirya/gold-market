package com.enigmacamp.goldmarket

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.enigmacamp.goldmarket.fragments.AppBaseFragment
import com.enigmacamp.goldmarket.fragments.HistoryFragment
import com.enigmacamp.goldmarket.fragments.HomeFragment
import com.enigmacamp.goldmarket.fragments.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController

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
        const val TITLE_KEY = "title_key"
        const val HOME_TITLE = "Gold Market"
        const val HISTORY_TITLE = "History"
        const val PROFILE_TITLE = "Profile"
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
        navController.setGraph(
            navController.graph, bundleOf(
                BALANCE_KEY to customerBalance,
                TITLE_KEY to HOME_TITLE
            )
        )

        homeFragment = HomeFragment()
        historyFragment = HistoryFragment()
        profileFragment = ProfileFragment()

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ic_home -> navController.navigate(
                    R.id.action_home_fragment,
                    bundleOf(BALANCE_KEY to customerBalance, TITLE_KEY to HOME_TITLE)
                )
                R.id.ic_history -> navController.navigate(
                    R.id.action_history_fragment,
                    bundleOf(TITLE_KEY to HISTORY_TITLE)
                )
                R.id.ic_profile -> navController.navigate(
                    R.id.action_profile_fragment,
                    bundleOf(PROFILE_KEY to authCustomer, TITLE_KEY to PROFILE_TITLE)
                )
            }
            true
        }
    }
}