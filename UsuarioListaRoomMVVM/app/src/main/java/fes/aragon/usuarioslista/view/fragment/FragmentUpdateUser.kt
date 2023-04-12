package fes.aragon.usuarioslista.view.fragment


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import fes.aragon.usuarioslista.R
import fes.aragon.usuarioslista.model.User


class FragmentUpdateUser(private val user: User) : DialogFragment() {

    internal lateinit var listener: NoticeDialogListener

    interface NoticeDialogListener {
        fun onDialogUpdate(dialog: DialogFragment,user: User)
    }

    private lateinit var buttonCancel: Button
    private lateinit var buttonUpdate: Button
    private lateinit var name: TextView
    private lateinit var email: TextView
    private lateinit var age: TextView
    private lateinit var masculino: RadioButton
    private lateinit var femenino: RadioButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_add_usuario, container, false)
        name = view.findViewById(R.id.name)
        email = view.findViewById(R.id.email)
        age = view.findViewById(R.id.age)

        name.text = user.name
        email.text = user.email
        age.text = user.age.toString()

        if (user.sex == "Masculino")

        buttonCancel = view.findViewById(R.id.cancelar)
        buttonCancel.setOnClickListener {
            dismiss()
        }

        buttonUpdate = view.findViewById(R.id.almacenar)
        buttonUpdate.text = "UPDATE"
        buttonUpdate.setOnClickListener {
            listener.onDialogUpdate(this,User(user.id,name.text.toString(),email.text.toString(),age.text.toString().toInt(),sex.text.toString()))
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