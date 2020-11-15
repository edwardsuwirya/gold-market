package com.enigmacamp.goldmarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.enigmacamp.goldmarket.fragments.HistoryFragment
import com.enigmacamp.goldmarket.fragments.HomeFragment
import com.enigmacamp.goldmarket.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    lateinit var homeFragment: Fragment
    lateinit var historyFragment: Fragment
    lateinit var profileFragment: ProfileFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        homeFragment = HomeFragment()
        historyFragment = HistoryFragment()
        profileFragment = ProfileFragment()

        makeCurrentFragment(homeFragment, "Home")

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_home -> makeCurrentFragment(homeFragment, it.title.toString())
                R.id.ic_history -> makeCurrentFragment(historyFragment, it.title.toString())
                R.id.ic_profile -> makeCurrentFragment(profileFragment, it.title.toString())
            }
            true
        }

    }

    private fun makeCurrentFragment(fragment: Fragment, fragmentTitle: String) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_container, fragment)
            title  = fragmentTitle
            commit()
        }
}