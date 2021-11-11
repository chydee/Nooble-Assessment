package com.chidi.nooble.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Short(
    val audioPath: String,
    val creator: Creator?,
    val dateCreated: String,
    val shortID: String,
    val title: String
) : Parcelable