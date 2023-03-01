package fes.aragon.playermp3

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import fes.aragon.playermp3.databinding.MusicItemBinding

class MusicAdapter(private val musicList: List<Modelo>, private val itemClickListener: OnMusicClickListener) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnMusicClickListener {
        fun onMusicClick(music: Modelo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = MusicItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val holder = MusicViewHolder(itemBinding,parent.context)

        itemBinding.root.setOnClickListener {
            val position = holder.adapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION } ?: return@setOnClickListener
            itemClickListener.onMusicClick(musicList[position])
        }

        return holder
    }

    override fun getItemCount(): Int = musicList.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MusicViewHolder -> holder.bind(musicList[position])
        }
    }

    private inner class MusicViewHolder(val binding: MusicItemBinding, context: Context): BaseViewHolder<Modelo>(binding.root){
        override fun bind(item: Modelo) {
            binding.titleSound.text = item.nameFile
            binding.imageSound.setImageResource(item.nameImge)
        }
    }
}