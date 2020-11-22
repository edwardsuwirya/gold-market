package com.enigmacamp.goldmarket.ui.main.view.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.enigmacamp.goldmarket.data.model.Customer
import com.enigmacamp.goldmarket.data.model.CustomerBalance
import com.enigmacamp.goldmarket.R
import com.enigmacamp.goldmarket.ui.base.AppBaseActivity
import com.enigmacamp.goldmarket.ui.main.view.fragments.HistoryFragment
import com.enigmacamp.goldmarket.ui.main.view.fragments.HomeFragment
import com.enigmacamp.goldmarket.ui.main.view.fragments.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppBaseActivity() {
    lateinit var navController: NavController
    private var name = ""
    lateinit var authCustomer: Customer

    val TAG = "MainActivity"

    lateinit var homeFragment: Fragment
    lateinit var historyFragment: Fragment
    lateinit var profileFragment: ProfileFragment

    companion object {
        const val PROFILE_KEY = "profile_key"
        const val CUSTOMER_KEY = "customer_key"
        const val TITLE_KEY = "title_key"
        const val HOME_TITLE = "Gold Market"
        const val HISTORY_TITLE = "History"
        const val PROFILE_TITLE = "Profile"
        const val NEED_REFRESH_KEY = "need_refresh_key"
    }

    private fun initUi() {
        homeFragment = HomeFragment()
        historyFragment = HistoryFragment()
        profileFragment = ProfileFragment()

        navController = Navigation.findNavController(this, R.id.nav_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUi()

        authCustomer = intent.getParcelableExtra(SignInActivity.INTENT_AUTH_CUSTOMER_KEY)
        name = "${authCustomer.firstName} ${authCustomer.lastName}"
        Log.d(TAG, name)

        navController.setGraph(
            navController.graph, bundleOf(
                CUSTOMER_KEY to authCustomer,
                TITLE_KEY to HOME_TITLE
            )
        )

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ic_home -> navController.navigate(
                    R.id.action_home_fragment,
                    bundleOf(CUSTOMER_KEY to authCustomer, TITLE_KEY to HOME_TITLE)
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