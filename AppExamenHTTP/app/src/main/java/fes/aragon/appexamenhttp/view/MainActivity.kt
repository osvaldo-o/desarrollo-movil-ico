package fes.aragon.appexamenhttp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import fes.aragon.appexamenhttp.databinding.ActivityMainBinding
import fes.aragon.appexamenhttp.model.Result
import fes.aragon.appexamenhttp.viewmodel.DataViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PersonaAdapter
    private val list = ArrayList<Result>()
    private val dataViewModel: DataViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        dataViewModel.listaUsuarios.observe(this,{
            list.add(it.get(0))
            adapter.notifyDataSetChanged()
        })
        binding.agregarPersona.setOnClickListener {
            dataViewModel.agregarUsuario()
        }
    }

    private fun initRecyclerView() {
        adapter = PersonaAdapter(list)
        binding.recyclerView.adapter = adapter
    }

}