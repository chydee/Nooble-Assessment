package com.chidi.local.source

import com.chidi.data.model.ShortItemData
import com.chidi.data.source.LocalDataSource
import com.chidi.local.db.ShortsDatabase
import com.chidi.local.mapper.ShortItemLocalMapper
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject


class LocalDataSourceImpl @Inject constructor(
    private val mapper: ShortItemLocalMapper,
    private val businessLogic: NoobleBusinessLogic,
    private val database: ShortsDatabase
) : LocalDataSource {

    override fun getData(): List<ShortItemData> {
        return businessLogic().map { item -> mapper.mapLocalToData(item) }
    }

    override fun addShorts(shortItemData: ShortItemData): Completable {
        return database.dao().addShort(mapper.mapDataToLocal(shortItemData))
    }

    override fun getAllShorts(): Single<List<ShortItemData>> {
        return database.dao().getAllShorts().map { items ->
            items.map { mapper.mapLocalToData(it) }
        }
    }

}
