package fes.aragon.oauth.view.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import fes.aragon.oauth.model.User
import fes.aragon.oauth.model.local.UserEntity

class DeleteFragment(private val user: UserEntity) : DialogFragment() {

    private lateinit var listener: NoticeDialogListener

    interface NoticeDialogListener {
        fun onDialogDeletedClick(dialog: DialogFragment, user: UserEntity)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)

            builder.setMessage("Delete user ${user.name}?")
                .setPositiveButton("Eliminated",
                    DialogInterface.OnClickListener { dialog, id ->
                        listener.onDialogDeletedClick(this, user)
                    })
                .setNegativeButton("Cancel",
                    DialogInterface.OnClickListener { dialog, id ->
                    })

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as NoticeDialogListener
        } catch (e: ClassCastException){
            throw ClassCastException((context.toString()))
        }
    }
}