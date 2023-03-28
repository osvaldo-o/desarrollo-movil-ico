package fes.aragon.app.viewmodel

import android.media.MediaPlayer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fes.aragon.app.model.DataModel
import fes.aragon.app.model.Modelo
import fes.aragon.app.model.Provedor

class DataViewModel : ViewModel() {
    val listMusic = MutableLiveData<List<Modelo>>()
    val playList = MutableLiveData<List<Modelo>>()

    fun fillListMusic() {
        Provedor.fillListMusic()
    }

    fun getListMusic () {
        listMusic.postValue(Provedor.listMusic)
    }

    fun getPlayList() {
        playList.postValue(Provedor.playList)
    }
}