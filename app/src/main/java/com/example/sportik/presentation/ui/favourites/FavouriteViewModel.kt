package com.example.sportik.presentation.ui.favourites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportik.domain.interactor.FavouritesInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel  @Inject constructor(private val interactor: FavouritesInteractor) : ViewModel() {

    private val newsLiveData = MutableLiveData<FavouriteScreenState>()
    fun getStateLiveData(): LiveData<FavouriteScreenState> {
        return newsLiveData
    }


    fun getNews() {
        viewModelScope.launch {
            interactor.getAll().collect { news ->
                when (news.size) {
                    0 -> newsLiveData.postValue(FavouriteScreenState.NothingFound)
                    else  -> newsLiveData.postValue(FavouriteScreenState.Data(news.toMutableList()))
                }
            }
        }
    }
}