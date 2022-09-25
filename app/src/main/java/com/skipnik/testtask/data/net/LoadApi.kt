package com.skipnik.testtask.data.net

import retrofit2.http.GET

interface LoadApi {

    @GET("/prod")
    suspend fun response() : Response

    companion object{
        const val BASE_URL = "https://efs5i1ube5.execute-api.eu-central-1.amazonaws.com"
    }
}