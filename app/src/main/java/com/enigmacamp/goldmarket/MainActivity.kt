package com.enigmacamp.goldmarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var homeFragment: HomeFragment = HomeFragment()

        loadFragment(homeFragment)

        var bottomNavigationView: BottomNavigationView = findViewById(R.id.bn_main)

        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }

    private fun loadFragment(fragment: Fragment): Boolean {
        if(fragment != null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fl_container, fragment)
                .commit()
            return true
        }

        return false
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when(item.itemId) {

            R.id.home_menu -> {
                val fragment = HomeFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fl_container, fragment, fragment.javaClass.getSimpleName())
                    .commit()
                title = item.title
                return@OnNavigationItemSelectedListener true
            }
            R.id.histori_menu -> {
                val fragment = HistoriFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fl_container, fragment, fragment.javaClass.getSimpleName())
                    .commit()
                title = item.title
                return@OnNavigationItemSelectedListener true
            }
            R.id.profil_menu -> {
                val fragment = ProfilFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fl_container, fragment, fragment.javaClass.getSimpleName())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false

    }
}