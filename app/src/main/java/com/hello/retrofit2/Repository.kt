package com.hello.retrofit2

import retrofit2.Response

class Repository {
    suspend fun getPost(): Response<Country>{ //просто получить
        return RetrofitInstance.api.getPost()
    }
    suspend fun getPost2(region: String): Response<Country>{ //получить с параметром
        return  RetrofitInstance.api.getPost2(region)
    }
    suspend fun getCustomPost(API_KEY: String, region: String): Response<List<Country>>{ //если есть ?parameter=something
        return RetrofitInstance.api.getCustomPost(API_KEY, region)
    }
}