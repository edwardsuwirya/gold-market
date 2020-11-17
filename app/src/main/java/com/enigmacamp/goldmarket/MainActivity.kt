package com.enigmacamp.goldmarket

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.enigmacamp.goldmarket.fragments.HistoryFragment
import com.enigmacamp.goldmarket.fragments.HomeFragment
import com.enigmacamp.goldmarket.fragments.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private val name = "Jhon Thor"
    private val gold_amount = "20"

    lateinit var homeFragment: Fragment
    lateinit var historyFragment: Fragment
    lateinit var profileFragment: ProfileFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        homeFragment = HomeFragment()
        historyFragment = HistoryFragment()
        profileFragment = ProfileFragment()

        makeCurrentFragment(homeFragment, "Gold Market")

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
        val profile: Array<String> = arrayOf(name, gold_amount)
        return profile
    }
}