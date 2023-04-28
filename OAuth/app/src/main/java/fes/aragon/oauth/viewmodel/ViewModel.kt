package fes.aragon.oauth.viewmodel

import androidx.lifecycle.*
import androidx.lifecycle.ViewModel
import androidx.room.CoroutinesRoom
import fes.aragon.oauth.model.User
import fes.aragon.oauth.model.local.UserApplication
import fes.aragon.oauth.model.local.UserEntity
import fes.aragon.oauth.model.local.toUser
import fes.aragon.oauth.model.remote.RetrofitClient
import fes.aragon.oauth.model.remote.UserJSON
import fes.aragon.oauth.model.remote.toUser
import fes.aragon.oauth.model.toUserEntity
import kotlinx.coroutines.*
import kotlin.concurrent.thread

class ViewModel() : ViewModel() {
    private val db = UserApplication.database.userDao()
    val users = MutableLiveData<List<User>>()
    val userUpdate = MutableLiveData<Pair<Int, User>>()
    val userDelete = MutableLiveData<User>()

    fun getUser() {
        viewModelScope.launch(Dispatchers.IO) {
            val call = RetrofitClient.webService.getUser(seed = "os${(1..10000).random()}")
            val body: UserJSON? = call.body()
            if (call.isSuccessful) {
                //val list = mutableListOf<User>()
                val user = body?.results?.get(0)!!.toUser()
                db.addUser(user.toUserEntity())
                users.postValue(db.getAllUser().map { it.toUser() })
                //list.add(user)
                //users.postValue(list)
            }
        }
    }

    fun getAllUser() {
        viewModelScope.launch(Dispatchers.IO) {
            users.postValue(db.getAllUser().map {
                it.toUser()
            })
        }
    }

    fun updateUser(user: User, position: Int) {
        viewModelScope.launch(Dispatchers.Default) {
            db.updateUser(user.toUserEntity())
        }.invokeOnCompletion {
            userUpdate.postValue(Pair(position, user))
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.Default) {
            db.deleteUser(user.toUserEntity())
        }.invokeOnCompletion {
            userDelete.postValue(user)
        }
    }

}
