package com.enigmacamp.goldmarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.enigmacamp.goldmarket.fragments.HomeFragment
import kotlinx.android.synthetic.main.activity_transaction.*
import kotlinx.android.synthetic.main.fragment_home.*

class TransactionActivity : AppCompatActivity() {

    lateinit var gold_amount_input: String
    lateinit var gold_amount_gr: TextView
    lateinit var price: TextView
    lateinit var totalPrice: TextView
    lateinit var totalTransaction: TextView
    lateinit var gold_amount: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)
        title = intent?.getStringExtra(HomeFragment.TRX_TYPE_KEY)

        gold_amount_input = "0"
        gold_amount_gr = findViewById(R.id.gold_amount_gr)
        gold_amount_gr.text = (gold_amount_input.toInt() / 9000000).toString()

        price = findViewById(R.id.price)
        totalPrice = findViewById(R.id.totalPrice)
        totalTransaction = findViewById(R.id.totalTransaction)
        gold_amount = findViewById(R.id.gold_amount)

        gold_amount_rp.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {

                var rupiah = s as Editable?
                price.text = "Rp" + rupiah
                totalPrice.text = "Rp" + rupiah
                totalTransaction.text = "Rp" + rupiah
                gold_amount_input = s.toString()
                gold_amount_gr.text = if (gold_amount_input.length > 0) {
                    (Math.floor(gold_amount_input.toDouble() / 900000)).toString()
                } else "0"

                gold_amount.text = if (gold_amount_input.length > 0) {
                    (Math.floor(gold_amount_input.toDouble() / 900000)).toString() + " gr emas"
                } else "0 gr emas"
            }
        })


    }
}