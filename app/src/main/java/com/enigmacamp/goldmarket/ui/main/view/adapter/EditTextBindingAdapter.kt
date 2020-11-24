package com.enigmacamp.goldmarket.ui.main.view.adapter

import android.text.TextWatcher
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText


object EditTextBindingAdapter {
    @BindingAdapter("textChangedListener")
    @JvmStatic
    fun bindTextWatcher(editText: TextInputEditText, textWatcher: TextWatcher?) {
        editText.addTextChangedListener(textWatcher)
    }

}