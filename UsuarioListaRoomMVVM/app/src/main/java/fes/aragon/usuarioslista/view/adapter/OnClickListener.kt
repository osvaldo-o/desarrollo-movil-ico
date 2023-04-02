package fes.aragon.usuarioslista.view.adapter

import fes.aragon.usuarioslista.model.User

interface OnClickListener {
    fun onClick(user: User, position: Int)
}