package fes.aragon.mvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import fes.aragon.mvvm.databinding.ActivityMainBinding
import fes.aragon.mvvm.viewmodel.DataViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dataViewModel: DataViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dataViewModel.currentModel.observe(this,{
            binding.name.text = it.name
            Glide.with(this)
                .load(it.image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .circleCrop()
                .circleCrop()
                .into(binding.image)
        })
        binding.container.setOnClickListener {
            dataViewModel.randomData()
        }
    }
}