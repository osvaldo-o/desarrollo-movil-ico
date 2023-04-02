package fes.aragon.usuarioslista.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fes.aragon.usuarioslista.model.User
import fes.aragon.usuarioslista.model.UserApplication
import kotlin.concurrent.thread

class ViewModelUser : ViewModel() {
    private val db = UserApplication.database.userDao()
    val users = MutableLiveData<MutableList<User>>()

    fun getAllUser() {
        thread {
            users.postValue(db.getAllUser())
        }
    }

    fun deleteUser(user: User) {
        thread {
            db.deleteUser(user)
            users.postValue(db.getAllUser())
        }
    }

    fun addUser(user: User) {
        thread {
            db.addUser(user)
            users.postValue(db.getAllUser())
        }
    }

    fun updateUser(user: User) {
        thread {
            db.updateUser(user)
            users.postValue(db.getAllUser())
        }
    }
}
