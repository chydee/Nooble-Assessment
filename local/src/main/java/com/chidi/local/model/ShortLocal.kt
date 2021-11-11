package com.chidi.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shorts_table")
data class ShortLocal(
    val audioPath: String,
    val creator: CreatorLocal,
    val dateCreated: String,
    @PrimaryKey val shortID: String,
    val title: String
)