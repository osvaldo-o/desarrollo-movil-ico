package fes.aragon.app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fes.aragon.app.model.DataModel
import fes.aragon.app.model.Provedor

class DataViewModel : ViewModel() {
    val listTop = MutableLiveData<List<DataModel>>()
    val listBottom = MutableLiveData<List<DataModel>>()

    fun getListTop() {
        listTop.postValue(Provedor.listTop)
    }

    fun getListBottom() {
        listBottom.postValue(Provedor.listBottom)
    }

    fun deleteItemListTop(position: Int) {
        val dog = Provedor.listTop.removeAt(position)
        Provedor.listBottom.add(dog)
    }
}