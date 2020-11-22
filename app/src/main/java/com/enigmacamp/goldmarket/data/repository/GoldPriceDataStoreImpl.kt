package com.enigmacamp.goldmarket.data.repository

import com.enigmacamp.goldmarket.data.model.GoldPrice
import java.util.*

class GoldPriceDataStoreImpl : GoldPriceDataStore {
    override fun getPrice(date: Date): GoldPrice? {
        return GoldPrice(894000.0, 913000.0)
    }
}