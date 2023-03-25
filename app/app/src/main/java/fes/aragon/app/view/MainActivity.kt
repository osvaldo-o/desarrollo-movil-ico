package fes.aragon.app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import fes.aragon.app.R
import fes.aragon.app.adapter.DogAdapter
import fes.aragon.app.databinding.ActivityMainBinding
import fes.aragon.app.model.Provedor
import fes.aragon.app.viewmodel.DataViewModel

class MainActivity : AppCompatActivity(), DogAdapter.OnDogClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dogAdapterUno: DogAdapter
    private lateinit var recyclerViewUno: RecyclerView
    private lateinit var dogAdapterDos: DogAdapter
    private lateinit var recyclerViewDos: RecyclerView
    private val dataViewModel: DataViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dataViewModel.currentModel.observe(this,{
            dogAdapterUno = DogAdapter(it,this)
            recyclerViewUno = binding.listView1
            recyclerViewUno.adapter = dogAdapterUno
            dogAdapterDos = DogAdapter(it, also {
            })
            recyclerViewDos = binding.listView1
            recyclerViewDos.adapter = dogAdapterDos
        })
    }

    override fun onDogClick(position: Int) {
        val dog = Provedor.Provedor.listView1.removeAt(position)
        Provedor.Provedor.listView2.add(dog)
    }
}