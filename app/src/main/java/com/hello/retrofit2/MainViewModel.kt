package com.hello.retrofit2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {
    val myResponse: MutableLiveData<Response<Country>> = MutableLiveData()
    val myResponse2: MutableLiveData<Response<Country>> = MutableLiveData()
    val myCustomPosts: MutableLiveData<Response<List<Country>>> = MutableLiveData()
    fun getPost(){ //просто получить
        viewModelScope.launch {
            val response = repository.getPost()
            myResponse.value = response
        }
    }
    fun getPost2(region: String){ //получить с параметром
        viewModelScope.launch {
            val response = repository.getPost2(region)
            myResponse2.value = response
        }
    }
    fun getCustomPosts(region: String, API_KEY: String){ //если есть ?parameter=something
        viewModelScope.launch {
            val response = repository.getCustomPost(region, API_KEY)
            myCustomPosts.value = response
        }
    }
}