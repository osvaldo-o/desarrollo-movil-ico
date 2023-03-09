package fes.aragon.playermp3

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fes.aragon.playermp3.databinding.ActivityDetailMusicBinding

class DetailMusic : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMusicBinding
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var music: Modelo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_music)
        binding = ActivityDetailMusicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        music = intent.getParcelableExtra("music")!!
        binding.titleSound.text = music.nameFile
        binding.imageView.setImageResource(music.nameImage)
        playerCurrent(music)

        /*intent.extras?.let {
            music = it.getSerializable("music") as Modelo
            binding.titleSound.text = music.nameFile
            binding.imageView.setImageResource(music.nameImage)
            playerCurrent(music)
        }*/

        binding.play.setOnClickListener {
            if (mediaPlayer == null){
                mediaPlayer = MediaPlayer().apply {
                    setDataSource(music.path)
                    prepare()
                    start()
                }
            }else{
                mediaPlayer!!.start()
            }
        }

        binding.stop.setOnClickListener {
            stopMusic()
        }

        binding.pause.setOnClickListener {
            if (mediaPlayer != null){
                mediaPlayer!!.pause()
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

    override fun onDestroy() {
        stopMusic()
        super.onDestroy()
    }

    private fun stopMusic(){
        if (mediaPlayer != null){
            mediaPlayer!!.release()
            mediaPlayer = null
        }
    }
}