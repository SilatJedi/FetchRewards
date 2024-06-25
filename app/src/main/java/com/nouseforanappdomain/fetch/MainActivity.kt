package com.nouseforanappdomain.fetch

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nouseforanappdomain.fetch.network.ListApi

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ListApi.getList(
            { listItems ->
                println(listItems.toString())
            },
            {
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
            }
        )
    }
}
