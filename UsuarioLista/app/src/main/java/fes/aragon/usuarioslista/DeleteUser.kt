package fes.aragon.usuarioslista

import android.annotation.SuppressLint
import android.app.Activity
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import java.io.File
import java.io.FileWriter
class DeleteUser(private val listUsers: MutableList<Usuario>, private val position: Int) : DialogFragment() {
    private lateinit var botonSalir: Button
    private lateinit var botonEliminar: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_delete_user, container, false)
        botonSalir = view.findViewById(R.id.cancelar)
        botonSalir.setOnClickListener {
            dismiss()
        }
        botonEliminar = view.findViewById(R.id.eliminar)
        botonEliminar.setOnClickListener {
            onDelete()
            dismiss()
        }
        return view
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        val activity: Activity? = activity
        if (activity is DialogInterface.OnDismissListener){
            (activity as DialogInterface.OnDismissListener).onDismiss(dialog)
        }
    }

    private fun onDelete() {
        listUsers.removeAt(position)
        overwriteFile()
    }

    private fun overwriteFile(){
        val sd = botonEliminar.context.filesDir
        File(sd.path.toString(),"usuarios.txt").delete()
        File("usuarios.txt").delete()
        val rutaLeer = File(sd.path.toString(),"usuarios.txt")
        val fileWriter = FileWriter(rutaLeer,true)
        listUsers.forEach {
            fileWriter.write("${it.id},${it.name},${it.url}\n")
        }
        fileWriter.close()
    }
}