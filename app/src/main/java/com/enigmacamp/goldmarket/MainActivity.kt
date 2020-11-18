package com.enigmacamp.goldmarket

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.enigmacamp.goldmarket.fragments.HistoryFragment
import com.enigmacamp.goldmarket.fragments.HomeFragment
import com.enigmacamp.goldmarket.fragments.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
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

        homeFragment = HomeFragment()
        historyFragment = HistoryFragment()
        profileFragment = ProfileFragment()

        var b = Bundle()
        b.putInt("gold_amount", gold_amount)
        homeFragment.arguments = b

        makeCurrentFragment(homeFragment, "Gold Market")

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ic_home -> {
                    var b = Bundle()
                    b.putInt("gold_amount", gold_amount)
                    homeFragment.arguments = b
                    makeCurrentFragment(homeFragment, it.title.toString())
                }
                R.id.ic_history -> makeCurrentFragment(historyFragment, it.title.toString())
                R.id.ic_profile -> {
                    var b = Bundle()
                    b.putParcelable(PROFILE_KEY, authCustomer)
                    b.putParcelable(BALANCE_KEY, customerBalance)
                    profileFragment.arguments = b
                    makeCurrentFragment(profileFragment, it.title.toString())
                }
            }
            true
        }

    }

    private fun makeCurrentFragment(fragment: Fragment, fragmentTitle: String) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_container, fragment)
            title = fragmentTitle
            commit()
        }

    fun getProfile(): Array<String> {
        val profile: Array<String> = arrayOf(name, gold_amount.toString())
        return profile
    }

    fun onBeliEmasClicked(view: View) {}
}