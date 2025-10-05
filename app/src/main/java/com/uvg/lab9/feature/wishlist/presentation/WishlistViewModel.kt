package com.uvg.lab9.feature.wishlist.presentation

import androidx.lifecycle.ViewModel
import com.uvg.lab9.feature.wishlist.domain.model.Product
import com.uvg.lab9.feature.wishlist.domain.model.WishlistUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class WishlistViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(WishlistUiState())
    val uiState: StateFlow<WishlistUiState> = _uiState

    // Cargar 10 productos de prueba
    fun loadProducts() {
        val products = List(10) { index ->
            Product(
                id = index + 1,
                name = "Producto ${index + 1}",
                isWishlisted = false
            )
        }
        _uiState.value = WishlistUiState(products = products)
    }

    // Alternar el estado de "isWishlisted" de un producto
    fun toggleWishlist(productId: Int) {
        _uiState.update { currentState ->
            val updatedProducts = currentState.products.map { product ->
                if (product.id == productId) {
                    product.copy(isWishlisted = !product.isWishlisted)
                } else {
                    product
                }
            }
            currentState.copy(products = updatedProducts)
        }
    }
}