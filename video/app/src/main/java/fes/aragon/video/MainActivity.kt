package fes.aragon.video

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.MediaController
import android.widget.Toast
import androidx.core.net.toUri
import fes.aragon.video.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var modelo: ArrayList<Modelo>
    private lateinit var adap: RecipeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val controller = MediaController(this)
        binding.surface.setMediaController(controller)
        controller.setAnchorView(binding.surface)
        fillList()

        binding.list.setOnItemClickListener(AdapterView.OnItemClickListener{ parent, view, position, id ->  
            val data: Modelo = modelo.get(position)
            var ruta = ""
            when (data.type) {
                1 -> {ruta = "android.resource://" + packageName + "/raw/" + data.namefile.removeRange(
                    data.namefile.indexOf('.'),
                    data.namefile.length
                )
                }
                2 -> {
                    ruta = data.namefile
                }
            }
            val rutaUri = ruta.toUri()
            binding.surface.setVideoURI(rutaUri)
            binding.surface.start()
            Toast.makeText(this,data.namefile,Toast.LENGTH_SHORT).show()
        })
    }
    private fun fillList(){
        modelo = ArrayList()
        modelo.add(Modelo("starwars.mp4",R.drawable.video_uno,1))
        modelo.add(Modelo("https://archive.org/download/ElephantsDream/ed_hd.mp4",R.drawable.video_dos,2))
        modelo.add(Modelo("clonwars.mp4",R.drawable.video_uno,1))
        adap = RecipeAdapter(this,modelo)
        binding.list.adapter = adap
    }
}