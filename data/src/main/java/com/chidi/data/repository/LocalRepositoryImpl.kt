package com.chidi.data.repository

import com.chidi.data.mapper.ShortItemMapper
import com.chidi.data.source.ShortDataSourceFactory
import com.desmondngwuta.domain.model.ShortItemDomain
import com.desmondngwuta.domain.repository.LocalRepository
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.rxkotlin.toObservable
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val dataSourceFactory: ShortDataSourceFactory,
    private val itemMapper: ShortItemMapper
) : LocalRepository {

    override fun getData(): Single<List<ShortItemDomain>> {
        return dataSourceFactory.local().getData().map { item -> itemMapper.mapDataToDomain(item) }.toObservable().toList()
    }

    override fun addShorts(shortItemDomain: ShortItemDomain): Completable {
        return dataSourceFactory.local()
            .addShorts(itemMapper.mapDomainToData(shortItemDomain))
    }

    override fun getAllShorts(): Single<List<ShortItemDomain>> {
        return dataSourceFactory.local().getAllShorts().map { shorts ->
            shorts.map { itemMapper.mapDataToDomain(it) }
        }
    }
}