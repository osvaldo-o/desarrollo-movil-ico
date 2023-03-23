package fes.aragon.mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fes.aragon.mvvm.model.DataModel
import fes.aragon.mvvm.model.Provedor

class DataViewModel : ViewModel() {
    val currentModel = MutableLiveData<DataModel>()
    fun randomData() {
        val valorActual: DataModel = Provedor.random()
        currentModel.postValue(valorActual)
    }
}