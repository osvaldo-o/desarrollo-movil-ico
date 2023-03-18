package fes.aragon.usuarioslista

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import fes.aragon.usuarioslista.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.File
import java.io.FileWriter
import java.io.InputStreamReader

class MainActivity : AppCompatActivity(), OnClickListener, FragmentDeleteUser.NoticeDialogListener, FragmentAddUser.NoticeDialogListener {

    private val USER_NOT_SELECT = -1
    private lateinit var binding: ActivityMainBinding
    private lateinit var userAdapter: UserAdapter
    private lateinit var recyclerView: RecyclerView
    private val users = mutableListOf<User>()
    private var userSelect = USER_NOT_SELECT
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        starComponents()
        binding.agregar.setOnClickListener {
            var fragmentAddUser = FragmentAddUser()
            fragmentAddUser.isCancelable = false
            fragmentAddUser.show(supportFragmentManager,"fragmentAddUser")
        }
        binding.delete.setOnClickListener {
            if (userSelect >= 0){
                val fragmentDeleteUser = FragmentDeleteUser(users,userSelect)
                fragmentDeleteUser.isCancelable = false
                fragmentDeleteUser.show(supportFragmentManager,"fragmentDeleteUser")
            }else{
                Toast.makeText(this,"Select a user",Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun starComponents() {
        userAdapter = UserAdapter(generateData(),this)
        recyclerView = binding.recyclerview
        recyclerView.adapter = userAdapter
    }

    private fun generateData(): MutableList<User>{
        users.clear()
        var us: User
        var data: List<String>
        val rutaLeer = File(this.filesDir.path.toString(),"usuarios.txt")
        if (rutaLeer.exists()){
            val file = InputStreamReader(openFileInput("usuarios.txt"))
            BufferedReader(file).useLines {
                it.forEach {
                    data = it.split(",")
                    us = User(data.get(0).toInt(),data.get(1),data.get(2))
                    users.add(us)
                }
            }
        }
        return users
    }
    override fun onClick(user: User, position: Int) {
        userSelect = position
    }

    private fun updateUser(user: User, position: Int){
        users[position] = User(user.id,user.name,user.url)
        overwriteFile()
        starComponents()
    }

    private fun overwriteFile(){
        File(this.filesDir.path.toString(),"usuarios.txt").delete()
        val rutaLeer = File(this.filesDir.path.toString(),"usuarios.txt")
        val fileWriter = FileWriter(rutaLeer,true)
        users.forEach {
            fileWriter.write("${it.id},${it.name},${it.url}\n")
        }
        fileWriter.close()
    }

    override fun onDialogDeletedClick(dialog: DialogFragment, position: Int) {
        users.removeAt(position)
        overwriteFile()
        userSelect = USER_NOT_SELECT
        starComponents()
    }

    override fun onDialogStoreClick(dialog: DialogFragment, user: User) {
        val rutaLeer = File(this.filesDir.path.toString(),"usuarios.txt")
        try {
            FileWriter(rutaLeer,true).use {
                val datos = StringBuffer()
                datos.append(user.id)
                    .append(",")
                    .append(user.name)
                    .append(",")
                    .append(user.url)
                    .append("\n")
                it.write(datos.toString())
                it.close()
                dialog.dismiss()
            }
        }catch (e:java.lang.Exception){
            Toast.makeText(this,"Error al leer el archivo",Toast.LENGTH_SHORT).show()
        }
        starComponents()
    }
}