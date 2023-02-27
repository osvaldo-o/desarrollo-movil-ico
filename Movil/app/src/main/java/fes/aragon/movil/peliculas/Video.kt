package fes.aragon.movil.peliculas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import androidx.core.net.toUri
import fes.aragon.movil.R
import fes.aragon.movil.databinding.ActivityVideoBinding

class Video : AppCompatActivity() {
    private lateinit var binding: ActivityVideoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val controller = MediaController(this)
        binding.surface.setMediaController(controller)
        controller.setAnchorView(binding.surface)
        val name = "clon_wars.mp4"
        val ruta = "android.resource://" + packageName + "/raw/" + name.removeRange(name.indexOf('.'), name.length)
        binding.surface.setVideoURI(ruta.toUri())
        binding.surface.start()
    }
}