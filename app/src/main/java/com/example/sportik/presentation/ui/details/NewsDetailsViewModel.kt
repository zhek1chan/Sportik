package com.example.sportik.presentation.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportik.data.Resource
import com.example.sportik.domain.interactor.NewsInteractor
import com.example.sportik.domain.model.NewsWithContent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsDetailsViewModel @Inject constructor(
    private val interactor: NewsInteractor
) : ViewModel() {

    private val newsLiveData = MutableLiveData<DetailsScreenState>()
    fun getStateLiveData(): LiveData<DetailsScreenState> {
        return newsLiveData
    }


    fun getNews(id: Int) {
        viewModelScope.launch {
            interactor.getNewsWithDetails(id).collect { news ->
                when (news) {
                    is Resource.Data -> newsLiveData.postValue(DetailsScreenState.SearchIsOk(news.value))
                    is Resource.ConnectionError -> newsLiveData.postValue(DetailsScreenState.ConnectionError)
                    is Resource.NotFound -> newsLiveData.postValue(DetailsScreenState.NothingFound)
                }
            }
        }
    }

    fun checkOnFav(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    fun addNewsToFav(item: NewsWithContent) {
        TODO("Not yet implemented")
    }

    fun deleteNewsToFav(id: Int) {
        TODO("Not yet implemented")
    }
}