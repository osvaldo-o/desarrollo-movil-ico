package fes.aragon.appexamenhttp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fes.aragon.appexamenhttp.model.*
import fes.aragon.appexamenhttp.model.local.UserApplication
import fes.aragon.appexamenhttp.model.local.toUser
import kotlinx.coroutines.launch

class ViewModel() : ViewModel() {
    private val db = UserApplication.database.userDao()
    val user = MutableLiveData<User>()
    fun getUser (seed: Int)  {
        viewModelScope.launch {
            val call = RetrofitClient.webService.getUser(seed = "os$seed")
            val body: UserJSON? = call.body()
            if (call.isSuccessful){
                user.postValue(body?.results?.get(0)?.toUser())
            }
        }
    }

    fun getAllUser() {
        viewModelScope.launch {
            db.getAllUser().forEach {
                user.postValue(it.toUser())
            }
        }
    }
}