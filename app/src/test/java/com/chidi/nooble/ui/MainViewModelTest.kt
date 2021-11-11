package com.chidi.nooble.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.chidi.data.repository.LocalRepositoryImpl
import com.chidi.nooble.di.RepositoryModule
import com.chidi.nooble.getOrAwaitValue
import com.chidi.nooble.mapper.ShortItemMapper
import com.chidi.nooble.ui.model.MainViewModel
import com.chidi.nooble.utils.Result
import com.desmondngwuta.domain.repository.LocalRepository
import com.desmondngwuta.domain.usecase.AddShortUseCase
import com.desmondngwuta.domain.usecase.GetAllShortsUseCase
import com.desmondngwuta.domain.usecase.GetDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.testing.*
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.annotation.Config
import javax.inject.Inject


@UninstallModules(RepositoryModule::class)
@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(AndroidJUnit4::class)
class MainViewModelTest {

    @Module
    @InstallIn(ActivityRetainedComponent::class)
    class TestRepositoryModule {

        @Provides
        fun localRepository(localRepositoryImpl: LocalRepositoryImpl): LocalRepository {
            return Mockito.mock(LocalRepository::class.java)
        }
    }

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @BindValue
    @JvmField
    val localRepository: LocalRepository = Mockito.mock(LocalRepository::class.java)

    @Inject
    lateinit var getDataUseCase: GetDataUseCase

    @Inject
    lateinit var addShortsUseCase: AddShortUseCase

    @Inject
    lateinit var getAllShortsUseCase: GetAllShortsUseCase

    @Inject
    lateinit var mapper: ShortItemMapper

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun testGetShortsList() {

        val viewModel = MainViewModel(getDataUseCase, addShortsUseCase, getAllShortsUseCase, mapper)
        viewModel.getAllShorts()
        val value = viewModel.allShorts.getOrAwaitValue()
        Assert.assertThat(value, Matchers.not(CoreMatchers.nullValue()))
    }

    @Test
    fun testGetShortsList_isNotEmpty() {

        val viewModel = MainViewModel(getDataUseCase, addShortsUseCase, getAllShortsUseCase, mapper)
        viewModel.getAllShorts()
        val value = viewModel.allShorts.getOrAwaitValue()
        if (value is Result.Success)
            assert(value.data.isNotEmpty())
    }

}