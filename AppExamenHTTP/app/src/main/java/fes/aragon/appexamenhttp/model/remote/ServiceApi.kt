package fes.aragon.appexamenhttp.model

import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {
    @GET("/api")
    suspend fun getUser(@Query("page") page: Int = 3,@Query("results") results: Int = 1,@Query("seed") seed: String) : Response<UserJSON>
}

object RetrofitClient {
    val webService: ServiceApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://randomuser.me")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(ServiceApi::class.java)
    }
}