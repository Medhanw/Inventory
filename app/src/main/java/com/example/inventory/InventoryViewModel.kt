package com.example.inventory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.inventory.data.Item
import com.example.inventory.data.ItemDao
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class InventoryViewModel(private val itemDao: ItemDao) : ViewModel() {

    val allItems: StateFlow<List<Item>> = itemDao.getAllItems()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addNewItem(name: String, price: String, count: String) {
        val newItem = getNewItemEntry(name, price, count)
        insertItem(newItem)
    }

    fun retrieveItem(id: Int): StateFlow<Item?> {
        return itemDao.getItem(id)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)
    }

    fun updateItem(id: Int, name: String, price: String, count: String) {
        val updatedItem = getUpdatedItemEntry(id, name, price, count)
        updateItem(updatedItem)
    }

    fun deleteItem(item: Item) {
        viewModelScope.launch {
            itemDao.delete(item)
        }
    }

    private fun insertItem(item: Item) {
        viewModelScope.launch {
            itemDao.insert(item)
        }
    }

    private fun updateItem(item: Item) {
        viewModelScope.launch {
            itemDao.update(item)
        }
    }

    private fun getNewItemEntry(name: String, price: String, count: String): Item {
        return Item(
            name = name,
            price = price.toDouble(),
            quantity = count.toInt()
        )
    }

    private fun getUpdatedItemEntry(id: Int, name: String, price: String, count: String): Item {
        return Item(
            id = id,
            name = name,
            price = price.toDouble(),
            quantity = count.toInt()
        )
    }

    fun isEntryValid(name: String, price: String, count: String): Boolean {
        return name.isNotBlank() && price.isNotBlank() && count.isNotBlank()
    }
}


