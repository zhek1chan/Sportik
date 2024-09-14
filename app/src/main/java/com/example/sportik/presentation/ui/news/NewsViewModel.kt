package com.example.sportik.presentation.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportik.data.Resource
import com.example.sportik.domain.interactor.NewsInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val interactor: NewsInteractor
) : ViewModel() {

    private val newsLiveData = MutableLiveData<NewsScreenState>()
    fun getStateLiveData(): LiveData<NewsScreenState> {
        return newsLiveData
    }


    fun getNews() {
        viewModelScope.launch {
            interactor.getNews().collect { news ->
                when (news) {
                    is Resource.Data -> newsLiveData.postValue(NewsScreenState.SearchIsOk(news.value.toMutableList()))
                    is Resource.ConnectionError -> newsLiveData.postValue(NewsScreenState.ConnectionError)
                    is Resource.NotFound -> newsLiveData.postValue(NewsScreenState.NothingFound)
                }
            }
        }
    }
}