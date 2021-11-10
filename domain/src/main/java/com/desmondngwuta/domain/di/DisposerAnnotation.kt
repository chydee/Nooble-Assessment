package com.desmondngwuta.domain.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainDisposeBag

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IODisposeBag
