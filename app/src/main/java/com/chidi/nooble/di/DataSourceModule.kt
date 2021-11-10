package com.chidi.nooble.di

import com.chidi.data.source.LocalDataSource
import com.chidi.local.source.LocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun localDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource
}