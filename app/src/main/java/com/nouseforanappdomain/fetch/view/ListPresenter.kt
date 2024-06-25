package com.nouseforanappdomain.fetch.view

import com.nouseforanappdomain.fetch.model.ListItem
import com.nouseforanappdomain.fetch.network.ListApi
import io.reactivex.subjects.PublishSubject

class ListPresenter {

    val uiStateSubject: PublishSubject<ListUiState> = PublishSubject.create()
    val dataState: PublishSubject<List<ListItem>> = PublishSubject.create()

    fun getList() {
        uiStateSubject.onNext(ListUiState.LOADING)

        ListApi.getList(
            { listItems ->
                dataState.onNext(listItems)
                uiStateSubject.onNext(ListUiState.DONE)
            },
            {
                uiStateSubject.onNext(ListUiState.ERROR)
            }
        )
    }
}