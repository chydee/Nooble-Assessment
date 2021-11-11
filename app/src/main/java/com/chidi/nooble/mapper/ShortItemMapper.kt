package com.chidi.nooble.mapper

import com.chidi.nooble.model.Short
import com.desmondngwuta.domain.model.ShortItemDomain
import javax.inject.Inject


class ShortItemMapper @Inject constructor() : Mapper<ShortItemDomain, Short> {
    override fun mapDomainToAppLayer(data: ShortItemDomain): Short {
        return with(data) {
            Short(
                audioPath,
                null,
                dateCreated,
                shortID,
                title
            )
        }
    }

    override fun mapAppToDomainLayer(data: Short): ShortItemDomain {
        return with(data) {
            ShortItemDomain(
                audioPath,
                null,
                dateCreated,
                shortID,
                title
            )
        }
    }
}