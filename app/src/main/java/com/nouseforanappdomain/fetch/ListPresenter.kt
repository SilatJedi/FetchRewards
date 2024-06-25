package com.nouseforanappdomain.fetch

import com.nouseforanappdomain.fetch.model.ListItem
import com.nouseforanappdomain.fetch.network.ListApi
import io.reactivex.subjects.PublishSubject

class ListPresenter {

    val uiStateSubject: PublishSubject<UiState> = PublishSubject.create()
    val dataState: PublishSubject<List<ListItem>> = PublishSubject.create()

    fun getList() {
        uiStateSubject.onNext(UiState.LOADING)

        ListApi.getList(
            { listItems ->
                dataState.onNext(listItems)
                uiStateSubject.onNext(UiState.DONE)
            },
            {
                uiStateSubject.onNext(UiState.ERROR)
            }
        )
    }

    enum class UiState {
        LOADING,
        ERROR,
        DONE
    }
}