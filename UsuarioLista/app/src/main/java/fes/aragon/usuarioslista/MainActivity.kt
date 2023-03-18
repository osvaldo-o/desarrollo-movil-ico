package fes.aragon.usuarioslista

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import fes.aragon.usuarioslista.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.File
import java.io.FileWriter
import java.io.InputStreamReader

class MainActivity : AppCompatActivity(), OnClickListener, DialogInterface.OnDismissListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var usuarioAdapter: UsuarioAdapter
    private lateinit var recyclerView: RecyclerView
    private val usuarios = mutableListOf<Usuario>()
    private var userSelect = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        starComponents()
        binding.agregar.setOnClickListener {
            var fragmentAddUsuario = fragment_add_usuario()
            fragmentAddUsuario.isCancelable = false
            fragmentAddUsuario.show(supportFragmentManager,"Datos de entrada")
        }
        binding.delete.setOnClickListener {
            if (userSelect >= 0){
                val fragmentDeleteUsuario = DeleteUser(usuarios,userSelect)
                fragmentDeleteUsuario.isCancelable = false
                fragmentDeleteUsuario.show(supportFragmentManager,"Datos entrada")
            }else{
                Toast.makeText(this,"Selecciona un usuario",Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun starComponents() {
        usuarioAdapter = UsuarioAdapter(generateData(),this)
        recyclerView = binding.recyclerview
        recyclerView.adapter = usuarioAdapter
    }

    private fun generateData(): MutableList<Usuario>{
        usuarios.clear()
        var us: Usuario
        var data: List<String>
        val rutaLeer = File(this.filesDir.path.toString(),"usuarios.txt")
        if (rutaLeer.exists()){
            val file = InputStreamReader(openFileInput("usuarios.txt"))
            BufferedReader(file).useLines {
                it.forEach {
                    data = it.split(",")
                    us = Usuario(data.get(0).toInt(),data.get(1),data.get(2))
                    usuarios.add(us)
                }
            }
        }
        return usuarios
    }
    override fun onClick(usuario: Usuario,position: Int) {
        userSelect = position
    }

    private fun onDelete(position: Int) {
        val user = usuarios.removeAt(position)
        overwriteFile()
        starComponents()
        Toast.makeText(this,"${user.name} deleted",Toast.LENGTH_SHORT).show()
    }

    private fun updateUser(user: Usuario,position: Int){
        usuarios[position] = Usuario(user.id,user.name,user.url)
        overwriteFile()
        starComponents()
    }

    private fun overwriteFile(){
        File(this.filesDir.path.toString(),"usuarios.txt").delete()
        val rutaLeer = File(this.filesDir.path.toString(),"usuarios.txt")
        val fileWriter = FileWriter(rutaLeer,true)
        usuarios.forEach {
            fileWriter.write("${it.id},${it.name},${it.url}\n")
        }
        fileWriter.close()
    }

    override fun onDismiss(dialog: DialogInterface?) {
        //Toast.makeText(this,"Update",Toast.LENGTH_SHORT).show()
        starComponents()
    }
}