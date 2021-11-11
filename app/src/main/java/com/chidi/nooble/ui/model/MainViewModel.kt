package com.chidi.nooble.ui.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chidi.nooble.mapper.ShortItemMapper
import com.chidi.nooble.model.Short
import com.chidi.nooble.utils.Event
import com.chidi.nooble.utils.Message
import com.chidi.nooble.utils.Result
import com.chidi.nooble.utils.asLiveData
import com.desmondngwuta.domain.usecase.AddShortUseCase
import com.desmondngwuta.domain.usecase.GetAllShortsUseCase
import com.desmondngwuta.domain.usecase.GetDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getDataUseCase: GetDataUseCase,
    private val addShortUseCase: AddShortUseCase,
    private val getAllShortsUseCase: GetAllShortsUseCase,
    private val mapper: ShortItemMapper
) : ViewModel() {

    private val disposeBag = CompositeDisposable()

    init {
        getShortsList()
    }

    private val _shortList = MutableLiveData<Result<List<Short>>>()
    val shorts get() = _shortList.asLiveData()

    private fun getShortsList() {

        getDataUseCase().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
            { items ->
                _shortList.postValue(Result.Success(items.reversed().map {
                    mapper.mapDomainToAppLayer(it)
                }))
            }, {
                _shortList.postValue(Result.Error(it))
            }
        ).let { disposeBag.add(it) }
    }

    private val _shortMessage = MutableLiveData<Event<Message>>()
    val addShortMessage get() = _shortMessage.asLiveData()

    fun addShortsToDatabase(shortItem: Short) {
        addShortUseCase(mapper.mapAppToDomainLayer(shortItem)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
            {
                _shortMessage.postValue(Event(Message("Added to bookmarks", false)))
            }, {}
        ).let {
            disposeBag.add(it)
        }
    }

    private val _shorts = MutableLiveData<Result<List<Short>>>()
    val allShorts get() = _shorts.asLiveData()

    fun getAllShorts() {
        getAllShortsUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _shorts.postValue(Result.Success(it.map { item ->
                    mapper.mapDomainToAppLayer(item)
                }))
            }, {
                _shorts.postValue(Result.Error(it))
            }).let {
                disposeBag.add(it)
            }
    }


    override fun onCleared() {
        disposeBag.dispose()
        disposeBag.clear()
        super.onCleared()
    }
}