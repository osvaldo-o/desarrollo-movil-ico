package fes.aragon.appexamenhttp

import com.google.gson.GsonBuilder
import fes.aragon.appexamenhttp.model.User
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

interface ServiceApi {
    @GET
    suspend fun getUser(@Url url: String) : Response<User>
}

object RetrofitCliente {
    val webService by lazy {
        Retrofit.Builder()
            .baseUrl("https://randomuser.me/api/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(ServiceApi::class.java)
    }
}