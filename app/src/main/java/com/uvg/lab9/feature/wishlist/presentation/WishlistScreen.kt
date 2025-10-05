package com.uvg.lab9.feature.wishlist.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uvg.lab9.feature.wishlist.domain.model.Product
import com.uvg.lab9.feature.wishlist.domain.model.WishlistUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishlistScreen(
    viewModel: WishlistViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    // Recolectar el estado
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    // Cargar productos una sola vez
    LaunchedEffect(Unit) {
        viewModel.loadProducts()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Wishlist") }
            )
        }
    ) { innerPadding ->
        // Mostrar lista de productos
        WishlistList(
            state = uiState.value,
            onToggle = { id -> viewModel.toggleWishlist(productId = id) },
            modifier = Modifier.padding(paddingValues = innerPadding)
        )
    }
}

@Composable
private fun WishlistList(
    state: WishlistUiState,
    onToggle: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    // Si no hay productos
    if (state.products.isEmpty()) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No hay productos aún"
            )
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(state.products, key = { it.id }) { product ->
                ProductRow(
                    product = product,
                    onToggle = { onToggle(product.id) }
                )
            }
        }
    }
}

@Composable
private fun ProductRow(
    product: Product,
    onToggle: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = product.name,
            modifier = Modifier.weight(1f)
        )

        IconButton(
            onClick = onToggle
        ) {
            if (product.isWishlisted) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Quitar de favoritos"
                )
            } else {
                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = "Agregar a favoritos"
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(0.5.dp))
}
