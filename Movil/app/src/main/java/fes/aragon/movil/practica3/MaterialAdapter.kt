package fes.aragon.movil.practica3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import fes.aragon.movil.R

class MaterialAdapter(private val context: Context, private val listaTarjetas: ArrayList<Card>) : RecyclerView.Adapter<MaterialAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var inicial: TextView
        var nombreTarjeta: TextView
        var imagenView: ImageView
        var card: CardView

        init {
            inicial = view.findViewById(R.id.inicial)
            nombreTarjeta = view.findViewById(R.id.name_tarjeta)
            imagenView = view.findViewById(R.id.image_view)
            card = view.findViewById(R.id.card_layout)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val li = LayoutInflater.from(parent.context)
        val v =li.inflate(R.layout.card_view_holder,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return if (listaTarjetas.isEmpty()){
            0
        }else{
            listaTarjetas.size
        }
    }

    override fun getItemId(position: Int): Long = listaTarjetas[position].id

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        animateCircularReveal(holder.itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name: String? = listaTarjetas[position].nombre
        val color: Int = listaTarjetas[position].color_recurso
        val inicialCaracter = holder.inicial
        val nameTextView = holder.nombreTarjeta
        val imagen = holder.imagenView
        inicialCaracter.setBackgroundColor(color)
        inicialCaracter.text = name?.substring(0,1)
        nameTextView.text = name
        imagen.setImageResource(R.drawable.libreria_img1)
        holder.card.setOnClickListener {
            Toast.makeText(context,"Carta $name",Toast.LENGTH_SHORT).show()
        }
    }

    private fun animateCircularReveal(itemView: View) {
        val centroX = 0
        val centroY = 0
        val inicialRadius = 0.0f
        val finRadius = kotlin.math.max(itemView.width,itemView.height)
        val animacion =ViewAnimationUtils.createCircularReveal(itemView,centroX,centroY,inicialRadius,finRadius.toFloat())
        animacion.start()
    }

}