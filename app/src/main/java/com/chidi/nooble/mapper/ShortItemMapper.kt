package com.chidi.nooble.mapper

import com.chidi.nooble.model.Creator
import com.chidi.nooble.model.Short
import com.desmondngwuta.domain.model.CreatorDomain
import com.desmondngwuta.domain.model.ShortItemDomain
import javax.inject.Inject


class ShortItemMapper @Inject constructor() : Mapper<ShortItemDomain, Short> {
    override fun mapDomainToAppLayer(data: ShortItemDomain): Short {
        return with(data) {
            Short(
                audioPath,
                Creator(creatorDomain.email, creatorDomain.userID),
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
                CreatorDomain(creator.email, creator.userID),
                dateCreated,
                shortID,
                title
            )
        }
    }
}