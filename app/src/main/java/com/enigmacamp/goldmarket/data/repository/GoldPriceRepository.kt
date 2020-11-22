package com.enigmacamp.goldmarket.data.repository

import com.enigmacamp.goldmarket.data.model.GoldPrice
import java.util.*

class GoldPriceRepository private constructor() {
    private var dataStore: GoldPriceDataStore? = null

    fun init(dataStore: GoldPriceDataStore) {
        this.dataStore = dataStore
    }

    fun getGoldPrice(currDate: Date): GoldPrice? {
        return dataStore?.getPrice(currDate)
    }

    companion object {
        val instance by lazy { GoldPriceRepository() }
    }
}