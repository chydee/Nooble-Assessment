package com.chidi.data.mapper

import com.chidi.data.model.ShortItemData
import com.desmondngwuta.domain.model.ShortItemDomain
import javax.inject.Inject

class ShortItemMapper @Inject constructor() : Mapper<ShortItemData, ShortItemDomain> {
    override fun mapDataToDomain(data: ShortItemData): ShortItemDomain {
        return with(data) {
            ShortItemDomain(
                audioPath,
                creatorDomain = CreatorItemMapper().mapDataToDomain(creatorItemData!!),
                dateCreated,
                shortID,
                title,
            )
        }
    }

    override fun mapDomainToData(data: ShortItemDomain): ShortItemData {
        return with(data) {
            ShortItemData(
                audioPath,
                creatorItemData = CreatorItemMapper().mapDomainToData(creatorDomain),
                dateCreated,
                shortID,
                title,
            )
        }
    }
}