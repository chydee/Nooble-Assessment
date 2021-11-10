package com.chidi.data.repository

import com.chidi.data.mapper.ShortItemMapper
import com.chidi.data.source.ShortDataSourceFactory
import com.desmondngwuta.domain.model.ShortItemDomain
import com.desmondngwuta.domain.repository.LocalRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val dataSourceFactory: ShortDataSourceFactory,
    private val itemMapper: ShortItemMapper
) : LocalRepository {

    override fun getData(): List<ShortItemDomain> {
        return dataSourceFactory.local().getData().map { item -> itemMapper.mapDataToDomain(item) }
    }

    override fun addShorts(short: ShortItemDomain): Completable {
        return dataSourceFactory.local()
            .addShorts(itemMapper.mapDomainToData(short))
    }

    override fun getAllShorts(): Single<List<ShortItemDomain>> {
        return dataSourceFactory.local().getAllShorts().map { shorts ->
            shorts.map { itemMapper.mapDataToDomain(it) }
        }
    }
}