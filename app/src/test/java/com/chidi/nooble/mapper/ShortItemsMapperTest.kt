package com.chidi.nooble.mapper

import com.desmondngwuta.domain.model.CreatorDomain
import com.desmondngwuta.domain.model.ShortItemDomain
import org.hamcrest.core.Is
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ShortItemsMapperTest {

    private lateinit var item: ShortItemDomain
    private lateinit var itemMapper: ShortItemMapper
    private lateinit var creator: CreatorDomain

    @Before
    fun setup() {
        creator = CreatorDomain("abc@gmail.com", "1")
        item = ShortItemDomain(
            audioPath = "https://filesamples.com/samples/audio/m4a/sample4.m4a",
            creatorDomain = creator,
            dateCreated = "2021-Nov-03",
            shortID = "1",
            title = "Title 1",
        )
        itemMapper = ShortItemMapper()
    }

    @Test
    fun testMapDomainToAppLayer_nonNullObject() {

        val data = itemMapper.mapDomainToAppLayer(item)

        Assert.assertNotNull(data)
    }

    @Test
    fun testMapDomainToAppLayer_returnTypeDifferentFromInput() {

        val data = itemMapper.mapDomainToAppLayer(item)

        Assert.assertNotSame(data, Is.`is`(item))
    }
}