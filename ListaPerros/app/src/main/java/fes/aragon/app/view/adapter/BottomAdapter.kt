package fes.aragon.app.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import fes.aragon.app.R
import fes.aragon.app.databinding.ItemBinding
import fes.aragon.app.model.DataModel

class BottomAdapter(private val list: List<DataModel>) : RecyclerView.Adapter<BottomAdapter.ViewHolder>() {
    private lateinit var context: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var binding = ItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val vista = LayoutInflater.from(context).inflate(R.layout.item,parent,false)
        return ViewHolder(vista)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dog = list.get(position)
        with(holder){
            binding.name.text = dog.name
            Glide.with(context)
                .load(dog.image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .circleCrop()
                .into(binding.image)
        }
    }
}