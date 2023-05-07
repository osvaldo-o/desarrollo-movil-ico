package fes.aragon.oauth.viewmodel

import androidx.lifecycle.*
import androidx.lifecycle.ViewModel
import fes.aragon.oauth.model.User
import fes.aragon.oauth.model.local.UserApplication
import fes.aragon.oauth.model.local.UserEntity
import fes.aragon.oauth.model.local.toUser
import fes.aragon.oauth.model.remote.RetrofitClient
import fes.aragon.oauth.model.remote.UserJSON
import fes.aragon.oauth.model.remote.toUser
import fes.aragon.oauth.model.toUserEntity
import kotlinx.coroutines.*

class ViewModel() : ViewModel() {
    private val db = UserApplication.database.userDao()
    val users = MutableLiveData<List<UserEntity>>()

    fun getAllUser() {
        viewModelScope.launch(Dispatchers.IO) {
            users.postValue(db.getAllUser())
        }
    }

    fun addUser() {
        viewModelScope.launch {
            val call = RetrofitClient.webService.getUser(seed = "os${(1..10000).random()}")
            val body: UserJSON? = call.body()
            if (call.isSuccessful) {
                val user = body?.results?.get(0)!!.toUser()
                db.addUser(user.toUserEntity())
                users.postValue(db.getAllUser())
            }

        }
    }

    fun updateUser(user: UserEntity) {
        viewModelScope.launch {
            db.updateUser(user)
            users.postValue(db.getAllUser())
        }
    }

    fun deleteUser(user: UserEntity) {
        viewModelScope.launch {
            db.deleteUser(user)
            users.postValue(db.getAllUser())
        }
    }

}
