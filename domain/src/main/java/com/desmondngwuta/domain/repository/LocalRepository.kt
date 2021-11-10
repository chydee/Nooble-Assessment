package com.desmondngwuta.domain.repository

import io.reactivex.Single

interface LocalRepository {
    fun getData(): Single<List<Short>>
}