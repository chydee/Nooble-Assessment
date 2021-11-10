package com.chidi.data.source

import javax.inject.Inject


class ShortDataSourceFactory @Inject constructor(
    private val localDataSource: LocalDataSource,
) {
    fun local() = localDataSource
}
