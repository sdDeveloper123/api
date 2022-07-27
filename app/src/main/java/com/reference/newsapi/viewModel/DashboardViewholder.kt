package com.reference.newsapi.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reference.newsapi.apis.NewsApi
import com.reference.newsapi.apis.newsResponse.Article
import com.reference.newsapi.utils.Constants.Companion.API_KEY
import kotlinx.coroutines.launch


class DashboardViewholder: ViewModel() {

    private val _status = MutableLiveData<String>()
    val status:LiveData<String>
    get() = _status

    private val _response = MutableLiveData<List<Article>>()
    val response: LiveData<List<Article>>
        get() = _response
    private val _navigateToSelectedProperty = MutableLiveData<Article?>()
    val navigateToSelectedProperty: LiveData<Article?>
        get() = _navigateToSelectedProperty

    fun getNews(){
         viewModelScope.launch {
             try {
                 val data = NewsApi.retrofitService.getHeadlines("in", API_KEY)
                 _status.value = "success"
                 _response.value = data.articles
             }catch (e:Exception){
                 _status.value = "FAILED $e"
             }

         }
     }
    fun displayPropertyDetails(id:Article) {
        _navigateToSelectedProperty.value = id
    }
    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

}