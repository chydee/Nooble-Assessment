package com.chidi.local.mapper


interface Mapper<I, O> {
    fun mapLocalToData(data: I): O
    fun mapDataToLocal(data: O): I
}