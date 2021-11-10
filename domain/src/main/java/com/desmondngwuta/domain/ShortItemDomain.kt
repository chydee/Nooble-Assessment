package com.desmondngwuta.domain

data class ShortItemDomain(
    val audioPath: String,
    val shortCreatorDomain: com.chidi.data.model.Creator,
    val dateCreated: String,
    val shortID: String,
    val title: String
)