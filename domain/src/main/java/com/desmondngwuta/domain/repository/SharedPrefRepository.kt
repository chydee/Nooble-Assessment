package com.desmondngwuta.domain.repository

import io.reactivex.Flowable
import io.reactivex.Observable


interface SharedPrefRepository {

    fun isFavourite(date: String): Observable<Boolean>

    fun setIsFavourite(date: String): Flowable<Unit>

    fun removeFavouriteFromePref(date: String): Flowable<Unit>
}