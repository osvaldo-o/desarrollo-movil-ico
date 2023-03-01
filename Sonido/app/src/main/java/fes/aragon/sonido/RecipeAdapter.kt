package fes.aragon.sonido

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class RecipeAdapter (context: Context, private val list: ArrayList<Modelo>): BaseAdapter() {
    private lateinit var inflater: LayoutInflater
    init {
        inflater = LayoutInflater.from(context)
    }
    override fun getCount(): Int = list.size

    override fun getItem(position: Int): Any? = null

    override fun getItemId(position: Int): Long = 1

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = inflater.inflate(R.layout.list_item,null)
        val file: TextView = view.findViewById(R.id.tile)
        val image: ImageView = view.findViewById(R.id.image_sound)
        file.text = list[position].nameFile
        image.setImageResource(list[position].nameImage)
        return  view

    }

}