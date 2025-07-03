package com.example.inventory

import android.app.Application
import com.example.inventory.data.InventoryDatabase

class InventoryApplication : Application() {
    val database: InventoryDatabase by lazy { InventoryDatabase.getDatabase(this) }
}
