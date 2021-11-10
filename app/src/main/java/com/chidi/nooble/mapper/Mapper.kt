package com.chidi.nooble.mapper

interface Mapper<I, O> {
    fun mapDomainToAppLayer(data: I): O
    fun mapAppToDomainLayer(data: O): I
}