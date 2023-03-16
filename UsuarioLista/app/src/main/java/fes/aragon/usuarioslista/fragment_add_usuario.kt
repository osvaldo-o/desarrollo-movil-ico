package fes.aragon.usuarioslista


import android.app.Activity
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import java.io.File
import java.io.FileWriter


class fragment_add_usuario() : DialogFragment() {

    private lateinit var botonSalir: Button
    private lateinit var botonEscribir: Button
    private lateinit var id: TextView
    private lateinit var nombre: TextView
    private lateinit var url: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_add_usuario, container, false)
        id = view.findViewById(R.id.id)
        nombre = view.findViewById(R.id.nombre)
        url = view.findViewById(R.id.url)
        botonSalir = view.findViewById(R.id.cancelar)
        botonSalir.setOnClickListener {
            dismiss()
        }
        botonEscribir = view.findViewById(R.id.almacenar)
        botonEscribir.setOnClickListener {
            val sd = botonSalir.context.filesDir
            val rutaLeer = File(sd.path.toString(),"usuarios.txt")
            try {
                FileWriter(rutaLeer,true).use {
                    val datos = StringBuffer()
                    datos.append(id.text)
                        .append(",")
                        .append(nombre.text)
                        .append(",")
                        .append(url.text)
                        .append("\n")
                    it.write(datos.toString())
                    it.close()
                    dismiss()
                }
            }catch (e:java.lang.Exception){
                Toast.makeText(botonSalir.context,"Error al leer el archivo",Toast.LENGTH_SHORT).show()
            }
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
}