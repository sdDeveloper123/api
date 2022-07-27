package com.reference.newsapi.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.reference.newsapi.apis.newsResponse.Article


class NewsViewModel (data: Article, app: Application): AndroidViewModel(app) {
    private val _selectedProperty = MutableLiveData<Article>()
    val selectedProperty: LiveData<Article>
        get() = _selectedProperty

    init {
        _selectedProperty.value = data
    }
}


class DetailViewModelFactory (
    private val data: Article,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            return NewsViewModel(data,application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}