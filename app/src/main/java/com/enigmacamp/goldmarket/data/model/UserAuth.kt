package com.enigmacamp.goldmarket.data.model

import android.os.Parcel
import android.os.Parcelable

data class UserAuth(var userName: String = "", var userPassword: String = "") : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(userName)
        parcel.writeString(userPassword)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserAuth> {
        override fun createFromParcel(parcel: Parcel): UserAuth {
            return UserAuth(parcel)
        }

        override fun newArray(size: Int): Array<UserAuth?> {
            return arrayOfNulls(size)
        }
    }
}