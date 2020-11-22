package com.enigmacamp.goldmarket.data.model

import android.os.Parcel
import android.os.Parcelable

data class CustomerBalance(var customerId: String = "", var goldInGram: Double = 0.0) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readDouble() ?: 0.0
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(customerId)
        parcel.writeDouble(goldInGram)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CustomerBalance> {
        override fun createFromParcel(parcel: Parcel): CustomerBalance {
            return CustomerBalance(parcel)
        }

        override fun newArray(size: Int): Array<CustomerBalance?> {
            return arrayOfNulls(size)
        }
    }
}