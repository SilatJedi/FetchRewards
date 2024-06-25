package com.nouseforanappdomain.fetch.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nouseforanappdomain.fetch.R
import com.nouseforanappdomain.fetch.adapter.ListAdapter
import io.reactivex.disposables.CompositeDisposable

class ListActivity : AppCompatActivity() {

    private var compositeDisposable = CompositeDisposable()
    private var presenter = ListPresenter()
    private var list: RecyclerView? = null
    private var listContainer: View? = null
    private var loadingContainer: View? = null
    private var errorContainer: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        listContainer = findViewById(R.id.list_container)
        loadingContainer = findViewById(R.id.loading_container)
        errorContainer = findViewById(R.id.error_container)

        list = findViewById(R.id.list)

        findViewById<Button>(R.id.retry_button)?.setOnClickListener {
            presenter.getList()
        }
    }

    override fun onStart() {
        super.onStart()

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
                        ListUiState.LOADING -> onLoading()

                        ListUiState.ERROR -> onError()

                        ListUiState.DONE -> onDone()

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
    
    private fun onLoading() {
        loadingContainer?.visibility = View.VISIBLE
        errorContainer?.visibility = View.GONE
        listContainer?.visibility = View.GONE
    }
    
    private fun onError() {
        loadingContainer?.visibility = View.GONE
        errorContainer?.visibility = View.VISIBLE
        listContainer?.visibility = View.GONE
    }
    
    private fun onDone() {
        loadingContainer?.visibility = View.GONE
        errorContainer?.visibility = View.GONE
        listContainer?.visibility = View.VISIBLE
    }
}
