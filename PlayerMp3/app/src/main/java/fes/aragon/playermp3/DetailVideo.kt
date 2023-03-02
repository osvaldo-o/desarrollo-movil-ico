package fes.aragon.playermp3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import androidx.core.net.toUri
import fes.aragon.playermp3.databinding.ActivityDetailVideoBinding

class DetailVideo : AppCompatActivity() {
    private lateinit var binding: ActivityDetailVideoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_video)
        binding = ActivityDetailVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val controller = MediaController(this)
        binding.surface.setMediaController(controller)
        controller.setAnchorView(binding.surface)
        intent.extras?.let {
            val model = it.getSerializable("music") as Modelo
            binding.surface.setVideoPath(model.path)
            binding.surface.start()
        }
    }
}