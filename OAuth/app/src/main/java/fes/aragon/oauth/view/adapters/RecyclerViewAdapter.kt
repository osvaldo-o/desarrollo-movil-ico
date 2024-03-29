package fes.aragon.oauth.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import fes.aragon.oauth.databinding.ItemUserBinding
import fes.aragon.oauth.model.User
import fes.aragon.oauth.model.local.UserEntity
import fes.aragon.oauth.view.adapters.BaseViewHolder

class RecyclerViewAdapter (private val list: List<UserEntity>, private val itemClickListener : OnUserClickListener) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnUserClickListener {
        fun onUserClick(userEntity: UserEntity)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = ItemUserBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(itemBinding, parent.context)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is ViewHolder -> {
                holder.bind(list[position])
                holder.onClick(list[position])
            }
        }
    }

    private inner class ViewHolder(val binding: ItemUserBinding,val context: Context) : BaseViewHolder<UserEntity>(binding.root) {
        override fun bind(item: UserEntity) {
            binding.name.text = item.name
            binding.last.text = item.last
            binding.email.text = item.email
            Glide.with(context)
                .load(item.image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(binding.photo)
        }

        override fun onClick(item: UserEntity) {
            binding.root.setOnClickListener {
                itemClickListener.onUserClick(item)
            }
        }
    }
}