package com.enigmacamp.goldmarket

import android.text.Editable
import android.text.TextWatcher

class DynamicTextWatcher(
    var afterChanged: (Editable?) -> Unit = {},
    var onChanged: (CharSequence?, Int, Int, Int) -> Unit = { _, _, _, _ -> },
    var beforeChanged: (CharSequence?, Int, Int, Int) -> Unit = { _, _, _, _ -> }
) : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        beforeChanged(s, start, count, after)
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        onChanged(s, start, before, count)
    }

    override fun afterTextChanged(s: Editable?) {
        afterChanged(s)
    }
}

fun AppTextWatcher(init: DynamicTextWatcher.() -> Unit): DynamicTextWatcher {
    val textWatcher = DynamicTextWatcher()
    return textWatcher.apply(init)
}