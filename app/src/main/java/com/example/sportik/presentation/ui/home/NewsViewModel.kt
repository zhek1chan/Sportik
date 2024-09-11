package com.example.sportik.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportik.data.Resource
import com.example.sportik.domain.interactor.NewsInteractor
import com.example.sportik.domain.model.News
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val interactor: NewsInteractor
) : ViewModel() {

    private val newsLiveData = MutableLiveData<List<News>>()
    fun observeOffers(): LiveData<List<News>> = newsLiveData

    fun getNews() {
        viewModelScope.launch {
            interactor.getNews().collect { news ->
                when (news) {
                    is Resource.Data -> newsLiveData.postValue(news.value)
                    is Resource.ConnectionError -> Unit
                    is Resource.NotFound -> Unit
                }
            }
        }
    }
}