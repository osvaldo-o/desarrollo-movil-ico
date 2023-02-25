package fes.aragon.video

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class RecipeAdapter(context: Context,private val list: ArrayList<Modelo>): BaseAdapter() {
    private lateinit var infliter : LayoutInflater
    init {
        infliter = LayoutInflater.from(context)
    }

    override fun getCount(): Int = list.size

    override fun getItem(position: Int): Any? = null

    override fun getItemId(position: Int): Long = 1

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = infliter.inflate(R.layout.list_item_row,null)
        val file: TextView = view.findViewById(R.id.title)
        val imagen: ImageView = view.findViewById(R.id.imagen_pel)
        file.text = list[position].namefile
        imagen.setImageResource(list[position].nameImagen)
        return view
    }
}