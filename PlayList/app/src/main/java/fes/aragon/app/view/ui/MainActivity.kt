package fes.aragon.app.view.ui

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import fes.aragon.app.view.adapter.TopAdapter
import fes.aragon.app.databinding.ActivityMainBinding
import fes.aragon.app.model.Modelo
import fes.aragon.app.model.Provedor
import fes.aragon.app.view.adapter.BottomAdapter
import fes.aragon.app.viewmodel.DataViewModel

class MainActivity : AppCompatActivity(), TopAdapter.OnDogClickListener, MediaPlayer.OnCompletionListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterListTop: TopAdapter
    private lateinit var recyclerViewTop: RecyclerView
    private lateinit var adapterListBottom: BottomAdapter
    private lateinit var recyclerViewBottom: RecyclerView
    private val mediaPlayer: MediaPlayer = MediaPlayer()
    private val dataViewModel: DataViewModel by viewModels()
    private var index = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dataViewModel.fillListMusic()
        dataViewModel.getListMusic()
        dataViewModel.listMusic.observe(this) {
            adapterListTop = TopAdapter(it,this)
            recyclerViewTop = binding.listView1
            recyclerViewTop.adapter = adapterListTop
        }
        dataViewModel.playList.observe(this) {
            adapterListBottom = BottomAdapter(it)
            recyclerViewBottom = binding.listView2
            recyclerViewBottom.adapter = adapterListBottom
        }

        binding.floatingPlay.setOnClickListener {
            mediaPlayer.reset()
            index = 0
            mediaPlayer.setOnCompletionListener(this)
            mediaPlayer.apply {
                setDataSource(Provedor.playList[index].path)
                prepare()
                start()
            }
        }


        binding.floatingClear.setOnClickListener {
            mediaPlayer.stop()
            Provedor.playList.clear()
            dataViewModel.getPlayList()
        }
    }

    override fun onDogClick(position: Int) {
        Provedor.playList.add(Provedor.listMusic[position])
        dataViewModel.getPlayList()
    }

    override fun onCompletion(mp: MediaPlayer?) {
        index++
        if (index == Provedor.playList.size){
            Toast.makeText(this,"Finish",Toast.LENGTH_SHORT).show()
            return
        }
        mediaPlayer.reset()
        mediaPlayer.setDataSource(Provedor.playList[index].path)
        mediaPlayer.prepare()
        mediaPlayer.start()
    }
}