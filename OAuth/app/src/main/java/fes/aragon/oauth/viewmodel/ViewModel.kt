package fes.aragon.oauth.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fes.aragon.oauth.model.User
import fes.aragon.oauth.model.local.UserApplication
import fes.aragon.oauth.model.local.UserEntity
import fes.aragon.oauth.model.local.toUser
import fes.aragon.oauth.model.remote.RetrofitClient
import fes.aragon.oauth.model.remote.UserJSON
import fes.aragon.oauth.model.remote.toUser
import fes.aragon.oauth.model.toUserEntity
import kotlinx.coroutines.launch

class ViewModel() : ViewModel() {
    private val db = UserApplication.database.userDao()
    val users = MutableLiveData<List<User>>()

    fun getUser ()  {
        viewModelScope.launch {
            val call = RetrofitClient.webService.getUser(seed = "os${(1..10000).random()}")
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