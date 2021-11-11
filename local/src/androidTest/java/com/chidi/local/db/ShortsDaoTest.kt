package com.chidi.local.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.chidi.local.model.CreatorLocal
import com.chidi.local.model.ShortLocal
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class ShortsDaoTest {

    private lateinit var database: ShortsDatabase
    private val context: Context = ApplicationProvider.getApplicationContext()
    private lateinit var shortItem: ShortLocal
    private lateinit var creator: CreatorLocal

    @Before
    fun init() {
        database =
            Room.inMemoryDatabaseBuilder(context, ShortsDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        creator = CreatorLocal("1", "abc@gmail.com")
        shortItem = ShortLocal(
            audioPath = "https://filesamples.com/samples/audio/m4a/sample4.m4a",
            title = "Starburst Galaxy M94 from Hubble",
            dateCreated = "2021-Nov-03",
            shortID = "1",
            creator = creator
        )
    }

    @Test
    fun test_GetsObjectFromDatabase() = runBlocking {
        database.dao().addShort(shortItem)
        val item = database.dao().getAllShorts()
        assertNotNull(item)
    }

    @Test
    fun test_GetsFirstObjectFromDatabase() = runBlocking {
        database.dao().addShort(shortItem)
        var itemm: ShortLocal = shortItem
        database.dao().getAllShorts().map {
            itemm = it[0]
        }
        assertEquals(shortItem, itemm)

    }

    @After
    fun tearDown() {
        database.close()
    }
}