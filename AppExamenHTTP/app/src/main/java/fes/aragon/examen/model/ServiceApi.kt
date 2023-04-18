package fes.aragon.examen

import fes.aragon.examen.model.Persona
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ServiceApi {
    @GET
    suspend fun getPersonas(@Url url: String) : Response<Persona>
}