package com.example.inventory.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.inventory.InventoryViewModel
import com.example.inventory.data.Item

@Composable
fun HomeScreen(
    viewModel: InventoryViewModel,
    onItemClick: (Int) -> Unit,
    onAddItemClick: () -> Unit
) {
    val itemList by viewModel.allItems.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddItemClick) {
                Text("+")
            }
        }
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            items(itemList.size) { index ->
                val item = itemList[index]
                ItemRow(item = item, onClick = { onItemClick(item.id) })
            }
        }
    }
}

@Composable
fun ItemRow(item: Item, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Column {
            Text(text = item.name, style = MaterialTheme.typography.titleMedium)
            Text(text = "Price: ₹${item.price} | Qty: ${item.quantity}")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    // Sample item
    val sampleItems = listOf(
        com.example.inventory.data.Item(1, "Apple", 20.0, 5),
        com.example.inventory.data.Item(2, "Banana", 5.0, 10)
    )

    // Dummy implementation for preview
    androidx.compose.material3.MaterialTheme {
        LazyColumn {
            items(sampleItems.size) { index ->
                ItemRow(item = sampleItems[index], onClick = {})
            }
        }
    }
}
