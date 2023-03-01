package fes.aragon.sonido

import android.media.MediaPlayer
import android.media.audiofx.EnvironmentalReverb
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.widget.Toast
import fes.aragon.sonido.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var archivos: ArrayList<Modelo>
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RecipeAdapter
    private var mediaPlayer: MediaPlayer? = null
    private var indice: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val stadoSD: String = Environment.getExternalStorageState()
        if (stadoSD == Environment.MEDIA_MOUNTED){
            print("Sistema: "+this.getExternalFilesDir(null))
            val ff = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).path)
            archivos = ArrayList()
            ff.listFiles()!!.forEach {
                if (it.isFile){
                    print(it)
                    archivos.add(Modelo(it.name,R.drawable.musica_img,it.path))
                }
            }
            archivos.add(Modelo("Magenta_Moon_Part_II.mp3",R.drawable.musica_img,"https://files.freemusicarchive.org/storage-freemusicarchive-org/music/no curator/Line Noise/Magenta Monn/Line Noise - 01 - Magenta Moon Part II.mp3"))
        }
        adapter = RecipeAdapter(this,archivos)
        binding.list.adapter = adapter

        binding.play.setOnClickListener {
            if (mediaPlayer == null){
                mediaPlayer = MediaPlayer().apply {
                    setDataSource(archivos.get(indice).path)
                    prepare()
                    start()
                }
            }else{
                mediaPlayer!!.start()
            }
        }

        binding.stop.setOnClickListener {
            if (mediaPlayer == null){
                mediaPlayer!!.release()
                mediaPlayer = null
            }
        }

        binding.pause.setOnClickListener {
            if (mediaPlayer != null){
                mediaPlayer!!.pause()
            }
        }

        binding.path.setOnClickListener {
            Toast.makeText(this,archivos.get(indice).path,Toast.LENGTH_SHORT).show()
        }

        binding.list.setOnItemClickListener { parent, view, position, id ->
            val data: Modelo = archivos.get(position)
            indice = position
            if (mediaPlayer == null){
                mediaPlayer = MediaPlayer().apply {
                    setDataSource(data.path)
                    prepare()
                    start()
                }
            }else{
                mediaPlayer!!.release()
                mediaPlayer = MediaPlayer().apply {
                    setDataSource(data.path)
                    prepare()
                    start()
                }
                Toast.makeText(this,data.nameFile,Toast.LENGTH_SHORT).show()
            }
        }
    }
}