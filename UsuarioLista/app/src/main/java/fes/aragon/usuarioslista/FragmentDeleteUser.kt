package fes.aragon.usuarioslista

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class FragmentDeleteUser(private val listUsers: MutableList<User>, private val position: Int) : DialogFragment() {

    internal lateinit var listener: NoticeDialogListener

    interface NoticeDialogListener {
        fun onDialogDeletedClick(dialog: DialogFragment,position: Int)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)

            builder.setMessage("Delete user  ${listUsers[position].name}?")
                .setPositiveButton("Eliminated",
                    DialogInterface.OnClickListener { dialog, id ->
                        listener.onDialogDeletedClick(this,position)
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