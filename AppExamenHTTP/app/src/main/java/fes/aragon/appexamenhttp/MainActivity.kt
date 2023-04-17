package fes.aragon.appexamenhttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import fes.aragon.appexamenhttp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RecyclerViewAdapter
    private val list = mutableListOf<Message>();
    private val remote = RetrofitCliente
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        getData()
    }

    private fun initRecyclerView() {
        adapter = RecyclerViewAdapter(list)
        binding.recyclerView.adapter = adapter
    }

    private fun getData() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = remote.webService.getPersonajes()
            val body: Personaje? = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    val personajes: List<Message> = body?.message?: emptyList()
                    list.clear()
                    list.addAll(personajes)
                    adapter.notifyDataSetChanged()
                }else{
                    msgError()
                }
            }
        }
    }

    private fun msgError() {
        Toast.makeText(this,"Error en conexion",Toast.LENGTH_SHORT).show()
    }
}