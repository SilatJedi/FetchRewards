package com.nouseforanappdomain.fetch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nouseforanappdomain.fetch.R
import com.nouseforanappdomain.fetch.model.ListItem

class ListAdapter(private val listItems: List<ListItem>): RecyclerView.Adapter<ViewHolder>() {
    override fun getItemCount(): Int = listItems.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listItems[position]
        val idText = "id: ${item.id}"
        val listIdText = "list id: ${item.listId}"
        val nameText = "name: ${item.name}"

        holder.id?.text  = idText
        holder.listId?.text = listIdText
        holder.name?.text = nameText
    }

}
