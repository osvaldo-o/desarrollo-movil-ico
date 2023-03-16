package fes.aragon.usuarioslista

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.data.InputStreamRewinder
import fes.aragon.usuarioslista.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.InputStreamReader
import java.text.FieldPosition
import kotlin.math.log

class MainActivity : AppCompatActivity(), OnClickListener, DialogInterface.OnDismissListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var usuarioAdapter: UsuarioAdapter
    private lateinit var recyclerView: RecyclerView
    private val usuarios = mutableListOf<Usuario>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        iniciar()
        binding.agregar.setOnClickListener {
            var fragmentAddUsuario = fragment_add_usuario()
            fragmentAddUsuario.isCancelable = false
            fragmentAddUsuario.show(supportFragmentManager,"Datos de entrada")
        }
    }

    private fun iniciar () {
        usuarioAdapter = UsuarioAdapter(generarDatos(),this)
        recyclerView = binding.recyclerview
        recyclerView.adapter = usuarioAdapter
    }

    private fun generarDatos(): MutableList<Usuario>{
        usuarios.clear()
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
    override fun onClick(usuario: Usuario,position: Int) {
        usuarios.removeAt(position)
        File(this.filesDir.path.toString(),"usuarios.txt").delete()
        val rutaLeer = File(this.filesDir.path.toString(),"usuarios.txt")
        val fileWriter = FileWriter(rutaLeer,true)
        usuarios.forEach {
            fileWriter.write("${it.id},${it.name},${it.url}\n")
        }
        fileWriter.close()
        Toast.makeText(this, "${usuario.name} eliminado", Toast.LENGTH_SHORT).show()
        iniciar()
    }

    override fun onDismiss(dialog: DialogInterface?) {
        Toast.makeText(this,"Update",Toast.LENGTH_SHORT).show()
        iniciar()
    }
}