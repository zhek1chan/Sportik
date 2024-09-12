package com.example.sportik.presentation.ui.home

import com.example.sportik.domain.model.News

sealed class NewsScreenState {
    data object NothingFound : NewsScreenState()
    data object ConnectionError : NewsScreenState()
    data class SearchIsOk(val data: MutableList<News>) : NewsScreenState()
}
