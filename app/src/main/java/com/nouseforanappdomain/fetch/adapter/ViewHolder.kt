package com.nouseforanappdomain.fetch.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nouseforanappdomain.fetch.R

class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val id: TextView? = view.findViewById(R.id.id)
    val listId: TextView? = view.findViewById(R.id.list_id)
    val name: TextView? = view.findViewById(R.id.name)
}