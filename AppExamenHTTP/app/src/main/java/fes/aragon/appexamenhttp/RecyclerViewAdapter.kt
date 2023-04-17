package fes.aragon.appexamenhttp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fes.aragon.appexamenhttp.databinding.ActivityMainBinding
import fes.aragon.appexamenhttp.databinding.ItemBinding

class RecyclerViewAdapter (val list: List<Message>) : RecyclerView.Adapter<BaseViewHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = ItemBinding.inflate(LayoutInflater.from(parent.context))
        val holder = ViewHolder(itemBinding,parent.context)
        return holder
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is ViewHolder -> holder.bind(list[position])
        }
    }

    private inner class ViewHolder(val binding: ItemBinding,val context: Context) : BaseViewHolder<Message>(binding.root) {
        override fun bind(item: Message) {
            Picasso.get().load(item.image).into(binding.image)
            binding.name.text = item.name
        }
    }
}