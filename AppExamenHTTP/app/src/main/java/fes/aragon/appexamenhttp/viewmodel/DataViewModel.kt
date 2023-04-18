package fes.aragon.appexamenhttp.viewmodel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fes.aragon.appexamenhttp.RetrofitCliente
import fes.aragon.appexamenhttp.model.Result
import fes.aragon.appexamenhttp.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataViewModel : ViewModel() {
    private var i: Int = 1
    val listaUsuarios = MutableLiveData<List<Result>>()

    fun agregarUsuario () {
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitCliente.webService.getUser("?page=1&results=3&seed=$i")
            val body: User? = call.body()
            if (call.isSuccessful){
                val usuarios : List<Result> = body?.results ?: emptyList<Result>()
                listaUsuarios.postValue(usuarios)
            }
            i++
        }
    }

}