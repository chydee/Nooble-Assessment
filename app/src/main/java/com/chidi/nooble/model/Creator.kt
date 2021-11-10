package com.chidi.nooble.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Creator(
    val email: String,
    val userID: String
) : Parcelable