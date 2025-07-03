package com.example.inventory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.inventory.ui.InventoryApp
import com.example.inventory.ui.theme.InventoryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val app = application as InventoryApplication
        val database = app.database
        setContent {
            InventoryTheme {
                InventoryApp(itemDao = database.itemDao())
            }
        }
    }
}
