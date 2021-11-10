package com.chidi.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chidi.data.model.ShortItemData
import com.chidi.local.model.ShortLocal
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ShortsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addShort(short: ShortLocal): Completable

    @Query("SELECT * FROM shorts_table")
   fun getAllShorts(): Single<List<ShortLocal>>
}