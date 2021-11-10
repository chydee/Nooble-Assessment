package com.chidi.data.mapper

import com.chidi.data.model.CreatorItemData
import com.desmondngwuta.domain.model.CreatorDomain
import javax.inject.Inject

class CreatorItemMapper @Inject constructor() : Mapper<CreatorItemData, CreatorDomain> {
    override fun mapDataToDomain(data: CreatorItemData): CreatorDomain {
        return with(data) {
            CreatorDomain(
                email,
                userID
            )
        }
    }

    override fun mapDomainToData(data: CreatorDomain): CreatorItemData {
        return with(data) {
            CreatorItemData(
                email,
                userID
            )
        }
    }
}