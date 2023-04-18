package fes.aragon.appexamenhttp.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fes.aragon.appexamenhttp.databinding.ItemUserBinding
import fes.aragon.appexamenhttp.model.Result

class RecyclerViewAdapter (val list: List<Result>) : RecyclerView.Adapter<BaseViewHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = ItemUserBinding.inflate(LayoutInflater.from(parent.context))
        val holder = ViewHolder(itemBinding,parent.context)
        return holder
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is ViewHolder -> holder.bind(list[position])
        }
    }

    private inner class ViewHolder(val binding: ItemUserBinding,val context: Context) : BaseViewHolder<Result>(binding.root) {
        override fun bind(item: Result) {
            Picasso.get().load(item.picture.large).into(binding.photo)
            binding.name.text = "${item.name.title} ${item.name.first}"
            binding.last.text = "${item.name.last}"
            binding.email.text = "${item.email}"
        }
    }
}