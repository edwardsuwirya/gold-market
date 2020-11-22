package com.enigmacamp.goldmarket.data.repository

import com.enigmacamp.goldmarket.data.model.GoldPrice
import java.util.*

interface GoldPriceDataStore {
    fun getPrice(date: Date): GoldPrice?
}