package com.chidi.data.mapper

import com.chidi.data.model.ShortItemData
import com.desmondngwuta.domain.model.ShortItemDomain
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */

class ShortItemMapper @Inject constructor() : Mapper<ShortItemData, ShortItemDomain> {
    override fun mapDataToDomain(data: ShortItemData): ShortItemDomain {
        return with(data) {
            ShortItemDomain(
                audioPath,
                shortCreatorData,
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
                shortCreatorDomain,
                dateCreated,
                shortID,
                title,
            )
        }
    }
}