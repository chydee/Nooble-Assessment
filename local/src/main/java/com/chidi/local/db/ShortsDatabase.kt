package com.chidi.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chidi.local.model.ShortLocal

@Database(version = 1, exportSchema = false, entities = [ShortLocal::class])
abstract class ShortsDatabase : RoomDatabase() {

    abstract fun dao(): ShortsDao
}