package fes.aragon.appexamenhttp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fes.aragon.appexamenhttp.model.RetrofitClient
import fes.aragon.appexamenhttp.model.Result
import fes.aragon.appexamenhttp.model.User
import kotlinx.coroutines.launch

class ViewModel : ViewModel() {
    val user = MutableLiveData<Result>()

    fun getUser (seed: Int) {
        viewModelScope.launch {
            val call = RetrofitClient.webService.getUser(seed = "os$seed")
            val body: User? = call.body()
            if (call.isSuccessful){
                user.postValue(body?.results?.get(0))
            }
        }
    }

}