package com.example.sportik.presentation.ui.details

import com.example.sportik.domain.model.NewsWithContent

sealed class DetailsScreenState {
    data object NothingFound : DetailsScreenState()
    data object ConnectionError : DetailsScreenState()
    data class SearchIsOk(val data: NewsWithContent) : DetailsScreenState()
}
