package com.example.inventory.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.inventory.InventoryViewModel
import com.example.inventory.InventoryViewModelFactory
import com.example.inventory.data.ItemDao
import com.example.inventory.ui.home.HomeScreen
import com.example.inventory.ui.item.ItemDetailsScreen
import com.example.inventory.ui.item.ItemEntryScreen

enum class InventoryScreen {
    Home,
    ItemEntry,
    ItemDetails
}

@Composable
fun InventoryApp(itemDao: ItemDao) {
    val navController: NavHostController = rememberNavController()
    val viewModel: InventoryViewModel = viewModel(
        factory = InventoryViewModelFactory(itemDao)
    )

    NavHost(
        navController = navController,
        startDestination = InventoryScreen.Home.name
    ) {
        composable(route = InventoryScreen.Home.name) {
            HomeScreen(
                viewModel = viewModel,
                onItemClick = { itemId ->
                    navController.navigate("${InventoryScreen.ItemDetails.name}/$itemId")
                },
                onAddItemClick = {
                    navController.navigate(InventoryScreen.ItemEntry.name)
                }
            )
        }

        composable(route = InventoryScreen.ItemEntry.name) {
            ItemEntryScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(route = "${InventoryScreen.ItemDetails.name}/{itemId}") { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId")?.toInt() ?: return@composable
            ItemDetailsScreen(
                itemId = itemId,
                viewModel = viewModel,
                onEditItem = {
                    navController.navigate("${InventoryScreen.ItemEntry.name}/$itemId")
                },
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
