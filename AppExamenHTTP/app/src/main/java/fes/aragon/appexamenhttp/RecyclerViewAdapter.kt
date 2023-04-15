package fes.aragon.appexamenhttp

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fes.aragon.appexamenhttp.databinding.ActivityMainBinding
import fes.aragon.appexamenhttp.databinding.ItemBinding

class RecyclerViewAdapter (val list: List<Message>) : RecyclerView.Adapter<BaseViewHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        TODO("Not yet implemented")
    }

    private inner class ViewHolder(val binding: ItemBinding,val context: Context) : BaseViewHolder<Message>(binding.root) {
        override fun bind(item: Message) {

        }
    }
}