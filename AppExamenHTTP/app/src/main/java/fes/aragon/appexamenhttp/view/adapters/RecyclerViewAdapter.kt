package fes.aragon.appexamenhttp.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import fes.aragon.appexamenhttp.databinding.ItemUserBinding
import fes.aragon.appexamenhttp.model.User

class RecyclerViewAdapter (val list: List<User>) : RecyclerView.Adapter<BaseViewHolder<*>>() {
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

    private inner class ViewHolder(val binding: ItemUserBinding,val context: Context) : BaseViewHolder<User>(binding.root) {
        override fun bind(item: User) {
            binding.name.text = item.name
            binding.last.text = item.last
            binding.email.text = item.name
            Glide.with(context)
                .load(item.image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .circleCrop()
                .into(binding.photo)
        }
    }
}