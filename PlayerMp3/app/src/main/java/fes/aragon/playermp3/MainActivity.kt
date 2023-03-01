package fes.aragon.playermp3

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import androidx.recyclerview.widget.RecyclerView
import fes.aragon.playermp3.databinding.ActivityMainBinding
import fes.aragon.playermp3.databinding.MusicItemBinding
import java.io.File

class MainActivity : AppCompatActivity(), MusicAdapter.OnMusicClickListener {

    private lateinit var listMusic: ArrayList<Modelo>
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MusicAdapter
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fillListMusic()
        adapter = MusicAdapter(listMusic,this)
        recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
    }

    override fun onMusicClick(music: Modelo) {
        //playerCurrent(music)
        val intent = Intent(this,DetailMusic::class.java)
        intent.putExtra("music",music)
        startActivity(intent)
    }

    private fun fillListMusic(){
        val stadoSD: String = Environment.getExternalStorageState()
        if (stadoSD == Environment.MEDIA_MOUNTED){
            val ff = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).path)
            listMusic = ArrayList()
            ff.listFiles()!!.forEach {
                if (it.isFile){
                    listMusic.add(Modelo(it.name,R.drawable.image_sound,it.path))
                }
            }
        }
    }

    private fun playerCurrent(music: Modelo){
        if (mediaPlayer == null){
            mediaPlayer = MediaPlayer().apply {
                setDataSource(music.path)
                prepare()
                start()
            }
        }else{
            mediaPlayer!!.release()
            mediaPlayer = MediaPlayer().apply {
                setDataSource(music.path)
                prepare()
                start()
            }
        }
    }

}