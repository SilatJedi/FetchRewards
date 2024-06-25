package com.nouseforanappdomain.fetch

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nouseforanappdomain.fetch.adapter.ListAdapter
import io.reactivex.disposables.CompositeDisposable

class ListActivity : AppCompatActivity() {

    private var compositeDisposable = CompositeDisposable()
    private var presenter = ListPresenter()
    private var list: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        list = findViewById(R.id.list)

        initializeSubscriptions()
        presenter.getList()
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }

    private fun initializeSubscriptions() {
        compositeDisposable.addAll(
            presenter.uiStateSubject.subscribe(
                { uiState ->
                    when (uiState) {
                        ListPresenter.UiState.LOADING -> {

                        }

                        ListPresenter.UiState.ERROR -> {

                        }

                        ListPresenter.UiState.DONE -> {

                        }

                        else -> {
                            onError()
                        }
                    }
                },
                {
                    onError()
                }
            ),
            presenter.dataState.subscribe(
                { listItems ->
                    list?.apply {
                        layoutManager = LinearLayoutManager(this@ListActivity)
                        adapter = ListAdapter(listItems)
                    }
                },
                {
                    onError()
                }
            )
        )
    }

    private fun onError() {
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
    }
}
