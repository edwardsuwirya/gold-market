package com.enigmacamp.goldmarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.enigmacamp.goldmarket.fragments.HistoryFragment
import com.enigmacamp.goldmarket.fragments.HomeFragment
import com.enigmacamp.goldmarket.fragments.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private var name = ""
    private var gold_amount = 0

    lateinit var authCustomer: Customer

    val TAG = "MainActivity"

    lateinit var homeFragment: Fragment
    lateinit var historyFragment: Fragment
    lateinit var profileFragment: ProfileFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        authCustomer = intent.getParcelableExtra(SignInActivity.INTENT_AUTH_CUSTOMER_KEY)
        name = "${authCustomer.firstName} ${authCustomer.lastName}"
        Log.d(TAG, name)

        gold_amount = intent.getParcelableExtra(SignInActivity.INTENT_CUSTOMER_BALANCE)
        Log.d(TAG, gold_amount.toString())

        homeFragment = HomeFragment()
        historyFragment = HistoryFragment()
        profileFragment = ProfileFragment()

        makeCurrentFragment(homeFragment, "Home")

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ic_home -> makeCurrentFragment(homeFragment, it.title.toString())
                R.id.ic_history -> makeCurrentFragment(historyFragment, it.title.toString())
                R.id.ic_profile -> {
                    var b = Bundle()
                    b.putStringArray("profile", getProfile())
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
}