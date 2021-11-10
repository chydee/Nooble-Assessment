package com.chidi.data.mapper

import com.chidi.data.model.ShortCreatorData
import com.chidi.data.model.ShortItemData
import com.desmondngwuta.domain.model.ShortItemDomain
import org.hamcrest.core.Is
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ShortItemMapperTest {

    private lateinit var data: ShortItemData
    private lateinit var creator: ShortCreatorData
    private lateinit var nasaItemMapper: ShortItemMapper

    @Before
    fun setup() {
        creator = ShortCreatorData("abc@gmail.com", "1")
        data = ShortItemData(
            audioPath = "https://filesamples.com/samples/audio/m4a/sample4.m4a",
            shortCreatorData = creator,
            dateCreated = "2021-Nov-03",
            shortID = "1",
            title = "Title 1",
        )
        nasaItemMapper = ShortItemMapper()
    }

    @Test
    fun testMapDataToDomain_nonNullObject() {

        val data = nasaItemMapper.mapDataToDomain(data)

        Assert.assertNotNull(data)
    }

    @Test
    fun testMapDataToDomain_returnTypeDifferentFromInput() {

        val data = nasaItemMapper.mapDataToDomain(data)

        Assert.assertNotSame(data, Is.`is`(data))
    }

    @Test
    fun testMapDataToDomain_returnTypeSameAsExpected() {

        val data = nasaItemMapper.mapDataToDomain(data)

        assert(data is ShortItemDomain)
    }
}