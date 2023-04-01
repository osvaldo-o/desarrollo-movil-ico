package fes.aragon.usuarioslista

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import fes.aragon.usuarioslista.databinding.UserItemBinding

class UserAdapter(private var user: MutableList<User>, private val listener: OnClickListener) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private lateinit var context: Context
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var binding = UserItemBinding.bind(view)

        fun onClick(user: User, position: Int){
            binding.root.setOnClickListener {
                listener.onClick(user,position)
            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val vista = LayoutInflater.from(context).inflate(R.layout.user_item,parent,false)
        return ViewHolder(vista)
    }
    override fun getItemCount(): Int = user.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val usuario = user.get(position)
        with(holder) {
            binding.nombreCliente.text = usuario.name
            binding.numCliente.text = usuario.id.toString()
            onClick(usuario,position)
            Glide.with(context)
                .load(usuario.url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .circleCrop()
                .into(binding.foto)
        }
    }

    fun listUser() : MutableList<User> = user

    fun setListUser(list : MutableList<User>){
        this.user = list
    }
}