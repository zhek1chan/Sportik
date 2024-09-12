package com.example.sportik.presentation.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportik.data.Resource
import com.example.sportik.domain.interactor.FavouritesInteractor
import com.example.sportik.domain.interactor.NewsInteractor
import com.example.sportik.domain.model.NewsWithContent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsDetailsViewModel @Inject constructor(
    private val interactor: NewsInteractor,
    private val favInteractor: FavouritesInteractor
) : ViewModel() {

    private var likeJob: Job? = null
    private var likeIndicator = MutableLiveData<Boolean>()
    fun getFavLiveData(): LiveData<Boolean> {
        return likeIndicator
    }
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

    fun onFavCheck(id: Int): LiveData<Boolean> {
        likeJob = viewModelScope.launch {
            favInteractor.favouritesCheck(id)
                .collect { value ->
                    likeIndicator.postValue(value)
                }
        }
        return likeIndicator
    }

    fun addNewsToFav(item: NewsWithContent) {
        favInteractor.add(item)
    }

    fun deleteNewsToFav(id: Int) {
        favInteractor.delete(id)
    }
}