package com.chidi.local.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, exportSchema = false, entities = [Short::class])
abstract class ShortsDatabase : RoomDatabase() {

    abstract fun dao(): ShortsDao
}