package fes.aragon.app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fes.aragon.app.model.DataModel
import fes.aragon.app.model.Provedor

class DataViewModel : ViewModel() {
    val currentModel = MutableLiveData<List<DataModel>>()
    fun getData(){
        val list = Provedor.Provedor.listView1
        currentModel.postValue(list)
    }
}