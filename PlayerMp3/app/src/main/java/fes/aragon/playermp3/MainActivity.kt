package fes.aragon.playermp3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import fes.aragon.playermp3.databinding.ActivityMainBinding
import java.io.File
import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity(), MusicAdapter.OnMusicClickListener {

    private lateinit var listMusic: ArrayList<Modelo>
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MusicAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        checkPermission()
    }

    private fun iniciar (){
        fillListMusic()
        adapter = MusicAdapter(listMusic,this)
        recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
    }

    override fun onMusicClick(music: Modelo) {
        var intent: Intent? = null
        when(music.extension){
            "mp3" -> {
                intent = Intent(this,DetailMusic::class.java)

            }
            "mp4" -> {
                intent = Intent(this,DetailVideo::class.java)
            }
        }
        intent?.putExtra("music",music)
        startActivity(intent)
    }

    private fun fillListMusic(){
        val stadoSD: String = Environment.getExternalStorageState()
        if (stadoSD == Environment.MEDIA_MOUNTED){
            val ff = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).path)
            listMusic = ArrayList()
            ff.listFiles()!!.forEach {
                if (it.isFile){
                    val nameImage = if (it.extension.equals("mp3")) R.drawable.image_sound else R.drawable.logo_video
                    listMusic.add(Modelo(it.name,nameImage,it.path,it.extension))
                }
            }
        }
    }

    private fun checkPermission (){
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            pedirPermisoLectura()
        }else{
            iniciar()
        }
    }

    private fun pedirPermisoLectura(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)){
            Toast.makeText(this,"Permiso rechazado",Toast.LENGTH_SHORT)
        }else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),1)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                iniciar()
            }else{
                Toast.makeText(this,"Permiso rechazado por primera vez",Toast.LENGTH_SHORT)
            }
        }
    }
}