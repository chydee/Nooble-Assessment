package com.chidi.nooble.di

import com.chidi.data.repository.LocalRepositoryImpl
import com.desmondngwuta.domain.repository.LocalRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun localRepository(localRepositoryImpl: LocalRepositoryImpl): LocalRepository
}