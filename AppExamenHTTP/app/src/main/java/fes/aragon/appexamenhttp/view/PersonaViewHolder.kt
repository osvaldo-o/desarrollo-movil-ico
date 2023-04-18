package fes.aragon.appexamenhttp.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fes.aragon.appexamenhttp.databinding.ItemBinding
import fes.aragon.appexamenhttp.model.Result

class PersonaViewHolder (view: View) : RecyclerView.ViewHolder(view){
    private val binding = ItemBinding.bind(view)
    fun bind(item : Result){
        Picasso.get().load(item.picture.large).into(binding.image)
        binding.nombre.text = "${item.name.title} ${item.name.first}"
        binding.apellido.text = item.name.last
        binding.correo.text = item.email
    }
}