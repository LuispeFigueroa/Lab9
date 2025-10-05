package com.uvg.lab9.feature.wishlist.domain.model

data class WishlistUiState(
    val products: List<Product> = emptyList()
)