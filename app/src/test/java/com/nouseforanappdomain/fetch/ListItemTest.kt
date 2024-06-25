package com.nouseforanappdomain.fetch

import com.nouseforanappdomain.fetch.model.ListItem
import org.junit.Test

import org.junit.Assert.*

class ListItemTest {
    @Test
    fun testInvalidListItem() {
        assertFalse(invalidItem.isValid)
    }

    @Test
    fun testValidItem() {
        assertTrue(validItem.isValid)
    }

    companion object {
        // pulled from response {"id": 755, "listId": 2, "name": ""}
        private val invalidItem = ListItem(755,2, "")
        // pulled from response {"id": 684, "listId": 1, "name": "Item 684"}
        private val validItem = ListItem(684,1, "Item 684")
    }
}