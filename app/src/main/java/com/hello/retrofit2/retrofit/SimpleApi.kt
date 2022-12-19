package com.hello.retrofit2.retrofit

import com.hello.retrofit2.model.Country
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SimpleApi {
    @GET("region/Europe") //получить по дефолту
    //моя ссылка: http://api.countrylayer.com/v2/region/Europe?access_key=7f7b5d0f0a8769ebdcc4a8ec2a4ea12e
    suspend fun getPost() : Response<Country>
    @GET("region/{region}") //с параметром
    suspend fun getPost2(
        @Path("region") region: String
    ): Response<Country>
    @GET("region/{region}") //если после вопроса в ссылке есть какой-то параметр со значением
    suspend fun getCustomPost(
        @Path("region") region: String,
        @Query("access_key") API_KEY: String
    ): Response<List<Country>>
}