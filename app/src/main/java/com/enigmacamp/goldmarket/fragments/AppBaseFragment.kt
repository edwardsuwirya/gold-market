package com.enigmacamp.goldmarket.fragments

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

open class AppBaseFragment : Fragment() {
    fun showActivityBar() {
        (activity as AppCompatActivity).supportActionBar?.show()
        (activity as AppCompatActivity).bottom_navigation.visibility = View.VISIBLE
    }

    fun hideActivityBar(){
        (activity as AppCompatActivity).supportActionBar?.hide()
        (activity as AppCompatActivity).bottom_navigation.visibility = View.GONE
    }

}