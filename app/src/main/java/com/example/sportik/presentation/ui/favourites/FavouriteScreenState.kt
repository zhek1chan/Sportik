package com.example.sportik.presentation.ui.favourites

import com.example.sportik.domain.model.NewsWithContent

sealed class FavouriteScreenState {
    data object NothingFound : FavouriteScreenState()
    data class Data(val data: MutableList<NewsWithContent>) : FavouriteScreenState()
}
