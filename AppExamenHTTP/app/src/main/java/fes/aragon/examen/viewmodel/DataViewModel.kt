package fes.aragon.examen.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fes.aragon.examen.ServiceApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import fes.aragon.examen.model.Result
import fes.aragon.examen.model.Persona
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DataViewModel : ViewModel() {
    private var i: Int = 1
    val listaUsuarios = MutableLiveData<List<Result>>()

    fun agregarPersona () {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ServiceApi::class.java).getPersonas("?page=3&results=1&seed=$i")
            val body: Persona? = call.body()
            if (call.isSuccessful){
                val usuarios : List<Result> = body?.results ?: emptyList<Result>()
                listaUsuarios.postValue(usuarios)
            }
            i++
        }
    }

    private fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://randomuser.me/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}