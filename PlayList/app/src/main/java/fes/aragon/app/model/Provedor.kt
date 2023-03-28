package fes.aragon.app.model

import android.os.Environment
import java.io.File

class Provedor {
    companion object {
        val listMusic: ArrayList<Modelo> = ArrayList<Modelo>()
        val playList: ArrayList<Modelo> = ArrayList()

        fun fillListMusic() {
            if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
                File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).path).listFiles()!!.forEach {
                    if (it.isFile){
                        listMusic.add(Modelo(it.name, it.path, it.extension))
                    }
                }
            }
        }

    }
}