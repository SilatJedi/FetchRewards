package com.nouseforanappdomain.fetch.model

data class ListItem(val id: Int, val listId: Int, val name: String?) {
    val isValid: Boolean get() = name?.isNotEmpty() == true
}