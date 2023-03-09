package fes.aragon.archivoslista

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import fes.aragon.archivoslista.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var listArchivos: ArrayList<Model>
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listArchivos = ArrayList()
        listArchivos.add(Model("Osvaldo Guerra Lanuza", "22"))
        adapter = Adapter(listArchivos)
        recyclerView = binding.recyclerView
        recyclerView.adapter = adapter

    }
}