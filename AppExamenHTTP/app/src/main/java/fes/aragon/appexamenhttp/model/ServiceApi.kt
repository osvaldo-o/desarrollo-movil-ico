package fes.aragon.appexamenhttp

import fes.aragon.appexamenhttp.model.Persona
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ServiceApi {
    @GET
    suspend fun getPersonas(@Url url: String) : Response<Persona>
}