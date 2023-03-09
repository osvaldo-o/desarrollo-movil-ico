package fes.aragon.archivoslista

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fes.aragon.archivoslista.databinding.ItemBinding

class Adapter(private val listArchivo: List<Model>) : RecyclerView.Adapter<BaseViewHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val holder = ModelViewHolder(itemBinding)
        return  holder
    }

    override fun getItemCount(): Int = listArchivo.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is ModelViewHolder -> holder.bind(listArchivo[position])
        }
    }

    private inner class ModelViewHolder(val binding: ItemBinding) : BaseViewHolder<Model>(binding.root){
        override fun bind(item: Model) {
            binding.name.text = item.name
            binding.edad.text = item.edad
        }

    }
}