package fes.aragon.usuarioslista


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment


class FragmentAddUser() : DialogFragment() {

    internal lateinit var listener: NoticeDialogListener

    interface NoticeDialogListener {
        fun onDialogStoreClick(dialog: DialogFragment,user: User)
    }

    private lateinit var buttonCancel: Button
    private lateinit var buttonWrite: Button
    private lateinit var id: TextView
    private lateinit var name: TextView
    private lateinit var url: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_add_usuario, container, false)
        id = view.findViewById(R.id.id)
        name = view.findViewById(R.id.nombre)
        url = view.findViewById(R.id.url)
        buttonCancel = view.findViewById(R.id.cancelar)
        buttonCancel.setOnClickListener {
            dismiss()
        }
        buttonWrite = view.findViewById(R.id.almacenar)
        buttonWrite.setOnClickListener {
            listener.onDialogStoreClick(this,User(id.text.toString().toInt(),name.text.toString(),url.text.toString()))
        }
        return view
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