package com.uvg.lab9

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.uvg.lab9.feature.wishlist.presentation.WishlistScreen
import com.uvg.lab9.navigation.AppNavigation
import com.uvg.lab9.ui.theme.Lab9Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab9Theme {
                AppNavigation()
            }
        }
    }
}