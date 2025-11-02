package com.ghostdev.wallpaperstudio.data.models

data class Category(
    val number: Int,
    val title: String,
    val subTitle: String,
    val image: Int
)

data class Favorite(
    val id: String,
    val title: String,
    val category: String,
    val image: Int,
    val isFavorite: Boolean = true
)