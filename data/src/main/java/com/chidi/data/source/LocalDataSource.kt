package com.chidi.data.source

import com.chidi.data.model.ShortItemData
import io.reactivex.Completable
import io.reactivex.Single


interface LocalDataSource {
    fun getData():List<ShortItemData>

    fun addShorts(shortItemData: ShortItemData): Completable

    fun getAllShorts(): Single<List<ShortItemData>>

}