package fes.aragon.usuarioslista

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import fes.aragon.usuarioslista.databinding.ActivityMainBinding

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
    }

    private fun generarDatos(): MutableList<Usuario>{
        val usuarios = mutableListOf<Usuario>()
        var us: Usuario
        for (i in 0 .. 10){
            if ((i%2) == 0){
                us = Usuario(i,"usuario"+i, "https://i.etsystatic.com/23512542/r/il/b41b53/3062879552/il_794xN.3062879552_fdfa.jpg")
            }else{
                us = Usuario(i,"usuario"+i, "https://i.etsystatic.com/23512542/r/il/b41b53/3062879552/il_794xN.3062879552_fdfa.jpg")
            }
            usuarios.add(us)
        }
        return usuarios
    }

    override fun onClick(usuario: Usuario) {
        Toast.makeText(this,usuario.name,Toast.LENGTH_SHORT).show()
    }
}