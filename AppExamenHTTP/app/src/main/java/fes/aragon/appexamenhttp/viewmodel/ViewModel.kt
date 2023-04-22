package fes.aragon.appexamenhttp.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import fes.aragon.appexamenhttp.model.*
import fes.aragon.appexamenhttp.model.local.UserApplication
import fes.aragon.appexamenhttp.model.local.UserEntity
import fes.aragon.appexamenhttp.model.local.toUser
import kotlinx.coroutines.launch

class ViewModel() : ViewModel() {
    private val db = UserApplication.database.userDao()
    val users = MutableLiveData<List<User>>()
    fun getUser (seed: Int)  {
        viewModelScope.launch {
            val call = RetrofitClient.webService.getUser(seed = "os$seed")
            val body: UserJSON? = call.body()
            if (call.isSuccessful){
                val list = mutableListOf<User>()
                val user = body?.results?.get(0)!!.toUser()
                list.add(user)
                users.postValue(list)
                db.addUser(UserEntity(name = user.name, last = user.last, email = user.email, image = user.image))
            }
        }
    }
    fun getAllUser() {
        viewModelScope.launch {
            users.postValue(db.getAllUser().map {
                it.toUser()
            })
        }
    }
    fun updateUser(user: User) {
        viewModelScope.launch {
            db.updateUser(user.toUserEntity())
        }
    }

    fun deleteUser(user: User){
        viewModelScope.launch {
            db.deleteUser(user.toUserEntity())
        }
    }
}