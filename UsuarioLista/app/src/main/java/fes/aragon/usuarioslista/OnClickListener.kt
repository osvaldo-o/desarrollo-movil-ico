package fes.aragon.usuarioslista

interface OnClickListener {
    fun onClick(usuario: Usuario, position: Int)
    fun onDelete(position: Int)
}