package fes.aragon.examen.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fes.aragon.examen.R
import fes.aragon.examen.model.Result

class PersonaAdapter (val list: List<Result>) : RecyclerView.Adapter<PersonaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PersonaViewHolder(layoutInflater.inflate(R.layout.item,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PersonaViewHolder, position: Int) {
        val item: Result = list[position]
        holder.bind(item)
    }

}