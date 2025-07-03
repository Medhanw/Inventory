package com.example.inventory.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp

// Default text styles used throughout the app
val AppTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 16.sp
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 20.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 24.sp
    )
)
