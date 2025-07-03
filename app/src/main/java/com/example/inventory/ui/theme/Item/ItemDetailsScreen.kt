package com.example.inventory.ui.item

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.inventory.InventoryViewModel

@Composable
fun ItemDetailsScreen(
    itemId: Int,
    viewModel: InventoryViewModel,
    onEditItem: () -> Unit,
    onNavigateBack: () -> Unit
) {
    val item by viewModel.retrieveItem(itemId).collectAsState()

    item?.let {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Name: ${it.name}", style = MaterialTheme.typography.titleLarge)
            Text("Price: ₹${it.price}")
            Text("Quantity: ${it.quantity}")

            Spacer(modifier = Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = { onEditItem() }) {
                    Text("Edit")
                }
                Button(onClick = {
                    viewModel.deleteItem(it)
                    onNavigateBack()
                }) {
                    Text("Delete")
                }
            }
        }
    }
}
