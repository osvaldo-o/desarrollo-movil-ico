package fes.aragon.usuarioslista

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import fes.aragon.usuarioslista.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var usuarioAdapter: UsuarioAdapter
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        usuarioAdapter = UsuarioAdapter(generarDatos(),this)
        recyclerView = binding.recyclerview
        recyclerView.adapter = usuarioAdapter
        binding.agregar.setOnClickListener {
            var fragmentAddUsuario = fragment_add_usuario()
            fragmentAddUsuario.isCancelable = false
            fragmentAddUsuario.show(supportFragmentManager,"Datos de entrada")
        }
    }
    private fun generarDatos(): MutableList<Usuario>{
        val usuarios = mutableListOf<Usuario>()
        var us: Usuario
        var data: List<String>
        val file = InputStreamReader(openFileInput("usuarios.txt"))
        val bufferedReader = BufferedReader(file)
        bufferedReader.useLines {
            it.forEach {
                data = it.split(",")
                us = Usuario(data.get(0).toInt(),data.get(1),data.get(2))
                usuarios.add(us)
            }
        }
        return usuarios
    }
    override fun onClick(usuario: Usuario) {
        Toast.makeText(this,usuario.name,Toast.LENGTH_SHORT).show()
    }
}