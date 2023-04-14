package fes.aragon.apprandomdog

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ServiceAPI {
    @GET
    suspend fun getAllBreed(@Url url: String) : Response<ModeloDog>

    @GET
    suspend fun getRandomBreed(@Url url: String) : Response<RandomDog>
}