package com.chidi.data.source

import com.chidi.data.model.ShortItemData
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow


interface LocalDataSource {
    fun getData():List<ShortItemData>

    fun addShorts(short: ShortItemData): Completable

    fun getAllShorts(): Single<List<ShortItemData>>

}