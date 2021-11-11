package com.desmondngwuta.domain.model

data class ShortItemDomain(
    val audioPath: String,
    val creatorDomain: CreatorDomain?,
    val dateCreated: String,
    val shortID: String,
    val title: String
)