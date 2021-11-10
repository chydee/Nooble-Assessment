package com.chidi.local.mapper

import com.chidi.data.model.ShortItemData
import com.chidi.local.model.ShortLocal
import javax.inject.Inject

class ShortItemLocalMapper @Inject constructor() : Mapper<ShortLocal, ShortItemData> {
    override fun mapLocalToData(data: ShortLocal): ShortItemData {
        return with(data) {
            ShortItemData(
                audioPath,
                null,
                dateCreated,
                shortID,
                title
            )
        }
    }

    override fun mapDataToLocal(data: ShortItemData): ShortLocal {
        return with(data) {
            ShortLocal(
                audioPath,
                dateCreated,
                shortID,
                title
            )
        }
    }
}