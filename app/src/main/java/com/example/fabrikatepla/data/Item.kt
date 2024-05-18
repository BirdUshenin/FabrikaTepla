package com.example.fabrikatepla.data

import CatalogTabAction
import CatalogTabStyle

data class Item(
    val id: Int,
    val category: String,
    val name: String,
    val description: String,
    val imageSrc: String,
    val style: CatalogTabStyle? = null,
    val action: CatalogTabAction? = null,
)