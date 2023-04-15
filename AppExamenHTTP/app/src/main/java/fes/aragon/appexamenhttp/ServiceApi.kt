package fes.aragon.appexamenhttp

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
interface ServiceApi {
    @GET("/personajes")
    suspend fun getPersonajes() : Response<Personaje>
}

object RetrofitCliente {
    val webService by lazy {
        Retrofit.Builder()
            .baseUrl("https://private-025ab1-osvaldo4.apiary-mock.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ServiceApi::class.java)
    }
}