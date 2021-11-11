package com.desmondngwuta.domain.repository

import com.desmondngwuta.domain.model.ShortItemDomain
import io.reactivex.Completable
import io.reactivex.Single

interface LocalRepository {
    fun getData(): Single<List<ShortItemDomain>>

    fun addShorts(shortItemDomain: ShortItemDomain): Completable

    fun getAllShorts(): Single<List<ShortItemDomain>>
}