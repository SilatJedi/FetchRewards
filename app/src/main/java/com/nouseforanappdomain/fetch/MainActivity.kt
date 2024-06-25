package com.nouseforanappdomain.fetch

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nouseforanappdomain.fetch.adapter.ListAdapter
import com.nouseforanappdomain.fetch.network.ListApi

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ListApi.getList(
            { listItems ->
                findViewById<RecyclerView>(R.id.list)?.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    adapter = ListAdapter(listItems)
                }
            },
            {
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
            }
        )
    }
}
